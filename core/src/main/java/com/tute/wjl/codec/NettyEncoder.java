package com.tute.wjl.codec;

import com.tute.wjl.entity.Message;
import com.tute.wjl.serialize.ProtostuffSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class NettyEncoder extends MessageToByteEncoder<Message> {
//    private JacksonSerializer serializer;
    private ProtostuffSerializer serializer;

    public NettyEncoder() {
        this.serializer = new ProtostuffSerializer();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        if (msg != null){
            byte[] data=serializer.serializer(msg);
            // 字节流总长度
            System.out.println("字节流总长度:"+data.length);
            // 定义的头部
            out.writeInt(4869);
            out.writeInt(data.length);
            out.writeBytes(data);
        }
    }
}
