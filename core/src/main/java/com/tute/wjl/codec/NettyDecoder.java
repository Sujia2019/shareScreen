package com.tute.wjl.codec;

import com.tute.wjl.entity.Message;
import com.tute.wjl.serialize.ProtostuffSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class NettyDecoder extends ByteToMessageDecoder {
    private ProtostuffSerializer serializer;
    private int LENGTH = 50000;

    public NettyDecoder() {
        this.serializer = new ProtostuffSerializer();
    }

    // 一个bytebuf一次传输1024个字节，发送图片需要拼接，即解决拆包问题
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if(in.readableBytes()>=8){
            // 符合协议
            int begin;
            while (true){
                // 开始的位置
                begin= in.readerIndex();
                // 标记
                in.markReaderIndex();
                // 读到了message的头部
                if(in.readInt()==4869){
                    break;
                }
                //没读到包头重置读取位置
                in.resetReaderIndex();
                //略过一个字节重新读
                in.readByte();
                if(in.readableBytes() < 8){
                    System.out.println("没有读到包头");
                    return;
                }
            }
            // 读取处消息长度
            int contentLength = in.readInt();
            if(in.readableBytes() < contentLength){
                System.out.println("消息的内容长度没有达到预期设定的长度，还原指针重新读");
                //消息的内容长度没有达到预期设定的长度，还原指针重新读
                in.readerIndex(begin);
                return;
            }
            // 创建这么大的字节数组
            byte[] data = new byte[contentLength];
            in.readBytes(data);
            Object obj=serializer.deSerializer(data, Message.class);
            out.add(obj);
        }
        System.out.println("不符合协议内容");

    }
}
