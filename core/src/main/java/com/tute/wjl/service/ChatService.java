package com.tute.wjl.service;

import com.tute.wjl.entity.Message;
import com.tute.wjl.entity.StuCourseLog;
import com.tute.wjl.net.NettyServer;
import com.tute.wjl.utils.Constants;
import com.tute.wjl.utils.MybatisConf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import org.apache.ibatis.session.SqlSession;

public class ChatService {
    private static ChatService instance;
    private SqlSession session = MybatisConf.getSqlSession();
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
        System.out.println(toId);
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
            // TODO 保存签到记录
            int logId = GroupService.courseLogMap.get(sm.getToId());
            if(sm.getMessageType().equals(Constants.ALL)){
                String content = (String)sm.getContent();
                if(content.contains("到")){
                    StuCourseLog stuCourseLog = new StuCourseLog();
                    stuCourseLog.setLogId(logId);
                    stuCourseLog.setStuAccount(sm.getFromId());
                    // 如果没有记录
                    if(null!=session.selectOne("com.tute.wjl.mapper.StuCourseLogMapper.getByStu",stuCourseLog)){
                        stuCourseLog.setContent(content);
                        String courseName = sm.getToId().split("-")[0];
                        String courseClass = sm.getToId().split("-")[1];
                        stuCourseLog.setCourseName(courseName);
                        stuCourseLog.setCourseClass(courseClass);
                        // 插入一条签到记录
                        session.insert("com.tute.wjl.mapper.StuCourseLogMapper.insert",stuCourseLog);
                        session.commit();
                    }
                }
            }
            cg.writeAndFlush(sm);
        }
    }

    // 广播
    public void sendMessageToAll(Message sm) {
        NettyServer.group.writeAndFlush(sm);
    }
}
