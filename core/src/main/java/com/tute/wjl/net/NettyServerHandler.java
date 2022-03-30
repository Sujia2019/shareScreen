package com.tute.wjl.net;

import com.tute.wjl.entity.Message;
import com.tute.wjl.utils.ChannelUtil;
import com.tute.wjl.utils.ThreadPoolUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadPoolExecutor;

public class NettyServerHandler extends SimpleChannelInboundHandler<Message> {
    private static final Logger LOGGER= LoggerFactory.getLogger(NettyServerHandler.class);
    private ThreadPoolExecutor serverHandlerPool;

    public NettyServerHandler() {
        this.serverHandlerPool= ThreadPoolUtil.ThreadPool(NettyServer.class.getSimpleName());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) {
        LOGGER.info("===服务端收到信息=== {}",message.toString());
        ctx.writeAndFlush(message);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.error(">>>>>>>>>>>>> rpc provider netty server caught exception.",cause);
        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            LOGGER.info(">>>>>>>>>>>>>>>>>>>>rpc provider netty server close an old channel.");
        }else{
            super.userEventTriggered(ctx,evt);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ChannelUtil.getChannels().setChannel(ctx.channel());
        LOGGER.info(">>>>>>>channel active"+ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ChannelUtil.getChannels().remove();
        LOGGER.info(">>>>>>>channel is not active"+ctx);
    }
}
