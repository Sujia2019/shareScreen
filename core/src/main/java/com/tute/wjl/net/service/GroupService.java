package com.tute.wjl.net.service;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 分组
public class GroupService {
    // 一个课堂一个组 开课时以班级为key
    public static Map<String, ChannelGroup> groupMap = new ConcurrentHashMap<>();

    // 注册进map中以实现私聊功能 userAccount,channelId
    public static Map<String, ChannelId> userMap = new ConcurrentHashMap<>();

    // 创建分组
    public void createGroup(String groupName){
        ChannelGroup cg = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        groupMap.put(groupName,cg);
    }

    // 销毁分组
    public void destoryGroup(String groupName){
        groupMap.get(groupName).close();
        groupMap.remove(groupName);
    }

    // 加入分组
    public boolean addGroup(String groupName, Channel channel){
        return groupMap.get(groupName).add(channel);
    }

    // 退出分组
    public boolean quitGroup(String groupName,Channel channel){
        return groupMap.get(groupName).remove(channel);
    }
}
