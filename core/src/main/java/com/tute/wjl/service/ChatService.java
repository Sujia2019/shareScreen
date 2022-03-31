package com.tute.wjl.service;

import com.tute.wjl.entity.Message;
import com.tute.wjl.net.NettyServer;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;

public class ChatService {
    private static ChatService instance;
    // 双检锁单例模型
    public static ChatService getInstance() {
        if(instance==null) {//先判断是否为null 后上锁进行初始化
            synchronized (ChatService.class) {
                if (instance == null)//将对象上锁之后再次判断 是否有别的线程初始化了
                    instance = new ChatService();
            }
        }
        return instance;
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
    }

    // 广播
    public void sendMessageToAll(Message sm) {
        NettyServer.group.writeAndFlush(sm);
    }
}
