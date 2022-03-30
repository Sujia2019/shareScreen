package com.tute.wjl.net;

import com.tute.wjl.entity.Message;
import com.tute.wjl.codec.NettyDecoder;
import com.tute.wjl.codec.NettyEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);

    // 用于接收客户端连接
    private EventLoopGroup bossGroup = new NioEventLoopGroup();
    // 用于处理IO
    private EventLoopGroup workGroup = new NioEventLoopGroup();

    // 所有人组
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    // 一个课堂一个组 开课时以班级为key


    public void init(int port){
        //param
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) {
                    ChannelPipeline pipeline = channel.pipeline();
                    group.add(channel);
                    channel.closeFuture().addListener((ChannelFutureListener) future -> group.remove(future.channel()));
                    pipeline.addLast(new NettyDecoder());
                    pipeline.addLast(new NettyEncoder());
                    pipeline.addLast(new NettyServerHandler());
                }
            }).childOption(ChannelOption.SO_KEEPALIVE, true);
            //bind
            ChannelFuture future = bootstrap.bind(port).sync();
            LOGGER.info(">>>>>>>>>>>> server start success , port ={}",port);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            LOGGER.info(">>>>>>>>>>>>>>>>> server stop");
        }finally {
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
