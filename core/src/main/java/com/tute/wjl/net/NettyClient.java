package com.tute.wjl.net;

import com.tute.wjl.context.DataContext;
import com.tute.wjl.context.FrameContext;
import com.tute.wjl.entity.Message;
import com.tute.wjl.codec.NettyDecoder;
import com.tute.wjl.codec.NettyEncoder;
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
    private FrameContext context;
    private DataContext dataContext;



    public NettyClient(FrameContext context,DataContext dataContext){
        this.context = context;
        this.dataContext = dataContext;
    }

    public boolean init(String serverHost,int port){
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
                                .addLast(new NettyClientHandler(context,dataContext));
                    }
                })
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.SO_BROADCAST, true) // UDP
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                .option(ChannelOption.SO_SNDBUF, 20 * 1024 * 1024);
        try {
            channel = bootstrap.connect(serverHost, port).sync().channel();
            if (!isValidate()) {
                close();
                return false;
            }
            LOGGER.debug(">>>>>>>>>>>>>>>>netty client proxy, connect to server success at host:{}, port:{}", serverHost, port);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    private void close() {
        if (this.channel != null && this.channel.isActive()) {
            this.channel.close();
        }
        if (this.group != null && !this.group.isShutdown()) {
            this.group.shutdownGracefully();
        }
        LOGGER.debug("netty client close");
    }

    private boolean isValidate() {
        if (this.channel != null) {
            return this.channel.isActive();
        }
        return false;
    }

    public void send(Message message) throws Exception {
        this.channel.writeAndFlush(message).sync();
    }
}
