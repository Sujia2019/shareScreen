package com.tute.wjl.net;

import com.dyuproject.protostuff.ByteString;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.tute.wjl.entity.Message;
import com.tute.wjl.utils.Constants;
import com.tute.wjl.utils.GzipUtils;
import com.tute.wjl.utils.ThreadPoolUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.concurrent.ThreadPoolExecutor;

public class NettyClientHandler extends SimpleChannelInboundHandler<Message> {
    private static final Logger LOGGER= LoggerFactory.getLogger(NettyClientHandler.class);
    private ThreadPoolExecutor clientHandlerPool;
    private JLabel jLabel;

    public NettyClientHandler(JLabel jLabel){
        clientHandlerPool = ThreadPoolUtil.ThreadPool(NettyServerHandler.class.getSimpleName());
        this.jLabel = jLabel;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception{
        if(message.getMessageType().equals(Constants.PICTURE)){
            // 进行图片资源的还原
            byte[] pic = GzipUtils.ungzip(message.getFileContent());
            jLabel.setIcon(new ImageIcon(pic));
        }else if(message.getMessageType().equals(Constants.ALL)){
            System.out.println(message.toString());
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.error(">>>>>>>>>>>rpc netty client caught exception",cause);
        ctx.close();
    }
}
