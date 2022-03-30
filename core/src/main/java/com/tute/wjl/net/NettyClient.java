package com.tute.wjl.net;

import com.tute.wjl.entity.Message;
import com.tute.wjl.codec.NettyDecoder;
import com.tute.wjl.codec.NettyEncoder;
import com.tute.wjl.ui.Client;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyClient.class);

    private EventLoopGroup group;
    private Channel channel;
    private Client client;

    public NettyClient(Client client){
        this.client = client;
    }

    public void init(String serverHost,int port) throws InterruptedException {
        this.group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline()
                                .addLast(new NettyEncoder())
                                .addLast(new NettyDecoder())
                                .addLast(new NettyClientHandler(client.getScreen()));
                    }
                })
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000);

        channel = bootstrap.connect(serverHost, port).sync().channel();
        if (!isValidate()) {
            close();
            return;
        }
        LOGGER.debug(">>>>>>>>>>>>>>>>rpc netty client proxy, connect to server success at host:{}, port:{}", serverHost, port);
    }

    private void close() {
        if (this.channel != null && this.channel.isActive()) {
            this.channel.close();
        }
        if (this.group != null && !this.group.isShutdown()) {
            this.group.shutdownGracefully();
        }
        LOGGER.debug("rpc netty client close");
    }

    private boolean isValidate() {
        if (this.channel != null) {
            return this.channel.isActive();
        }
        return false;
    }

    public void send(Message message) throws Exception {
        System.out.println("发送");
        this.channel.writeAndFlush(message).sync();
    }
}
