package com.tute.wjl.service;

import com.tute.wjl.entity.Message;
import com.tute.wjl.net.NettyServer;
import com.tute.wjl.utils.Constants;
import io.netty.channel.ChannelHandlerContext;
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
    public static Map<String, ChannelId>  userMap = new ConcurrentHashMap<>();

    private static GroupService instance;
    // 双检锁单例模型
    public static GroupService getInstance() {
        if(instance==null) {//先判断是否为null 后上锁进行初始化
            synchronized (GroupService.class) {
                if (instance == null){//将对象上锁之后再次判断 是否有别的线程初始化了
                    instance = new GroupService();

                }
            }
        }
        return instance;
    }


    // 创建分组
    public void createGroup(Message message,ChannelHandlerContext ctx){
        ChannelGroup cg = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        System.out.println("创建分组：分组ID "+message.getToId());
        groupMap.put(message.getToId(),cg);
        // ...记得把自己加入分组，，，
        message.setMessageType(Constants.CREATE_SUCCESS);
        ctx.writeAndFlush(message);
        addGroup(message,ctx);
    }

    // 销毁分组
    public void destroyGroup(Message message,ChannelHandlerContext ctx){
        ChannelGroup group = groupMap.get(message.getToId());
        // 移除自己
        group.remove(ctx.channel());
        // 发送关闭信息
        group.writeAndFlush(message);
        group.close();
        groupMap.remove(message.getToId());
    }

    // 加入分组
    public void addGroup(Message message, ChannelHandlerContext ctx){
        ChannelId channelId = userMap.get(message.getFromId());
        ChannelGroup cg = groupMap.get(message.getToId());
        if (cg != null){
            cg.add(NettyServer.group.find(channelId));
            // 返回加入成功的信息
            message.setMessageType(Constants.ADD_SUCCESS);
            ctx.writeAndFlush(message);
        }else{
            ctx.writeAndFlush(new Message("加入失败，暂时还没有开课哦～"));
        }
    }

    // 退出分组
    public void quitGroup(Message message,ChannelHandlerContext ctx){
        String toId = message.getToId();
        if(groupMap.containsKey(toId)){
            ChannelId channelId = userMap.get(message.getFromId());
            groupMap.get(toId).remove(NettyServer.group.find(channelId));
        }
    }
}
