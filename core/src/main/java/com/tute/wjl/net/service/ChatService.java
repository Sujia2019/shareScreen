package com.tute.wjl.net.service;

import com.tute.wjl.utils.Constants;
import com.tute.wjl.entity.Message;
import com.tute.wjl.net.NettyServer;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;

public class ChatService {
    public Object getObj(Object msg){
        Message sm = (Message)msg;
        String type = sm.getMessageType();
        if(type.equals(Constants.ALL) ){
            sendMessageToAll(sm);
        }else if(type.equals(Constants.PRIVATE)){
            sendMessageToOne(sm);
        } else if(type.equals(Constants.GROUP)){
            sendMessageToOne(sm);
        }
        return new Message("消息类型错误");

    }

    public void sendMessageToOne(Message sm) {
        String toId = sm.getToId();
        ChannelId id = GroupService.userMap.get(toId);
        Channel channel = NettyServer.group.find(id);
        if(channel.isActive()){
            //如果在线，发送即时消息
            channel.writeAndFlush(sm);
        }
    }

    public void sendMessageToGroup(Message sm) {
        String groupId = sm.getToId();
        ChannelGroup cg = GroupService.groupMap.get(groupId);
        if(cg!=null){
            cg.writeAndFlush(sm);
        }
        System.out.println("发送失败");
    }

    // 广播
    public void sendMessageToAll(Message sm) {
        NettyServer.group.writeAndFlush(sm);
    }
}
