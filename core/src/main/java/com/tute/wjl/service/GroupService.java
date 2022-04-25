package com.tute.wjl.service;

import com.tute.wjl.entity.CourseLog;
import com.tute.wjl.entity.Message;
import com.tute.wjl.entity.StuCourseLogInfo;
import com.tute.wjl.net.NettyServer;
import com.tute.wjl.utils.Constants;
import com.tute.wjl.utils.DateUtil;
import com.tute.wjl.utils.ExcelUtil;
import com.tute.wjl.utils.MybatisConf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 分组
public class GroupService {
    // 一个课堂一个组 开课时以班级为key
    public static Map<String, ChannelGroup> groupMap = new ConcurrentHashMap<>();
    public static Map<String, List<String>> groupWithUserNames = new ConcurrentHashMap<>();

    // 注册进map中以实现私聊功能 userAccount,channelId
    public static Map<String, ChannelId>  userMap = new ConcurrentHashMap<>();

    // 保存目前存储的课程记录 key为group组名，value为记录id
    public static Map<String,Integer> courseLogMap = new ConcurrentHashMap<>();

    // 操作数据库
    private SqlSession session = MybatisConf.getSqlSession();
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
        String toId = message.getToId();
        ChannelGroup cg = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        String courseName = toId.split("-")[0];
        String courseClass = toId.split("-")[1];
        // 创建开课记录
        // 如果暂时没有未结束课程，insert一条开课记录
        if(session.selectOne("com.tute.wjl.mapper.CourseLogMapper.getClassStatus",courseClass)==null){
            CourseLog courseLog = new CourseLog();
            courseLog.setTeacherAccount(message.getFromId());
            courseLog.setTeacherName(message.getFromName());
            String time = DateUtil.formatDateTime(new Date());
            courseLog.setCreateTime(time);
            courseLog.setLogName(message.getToId()+"-"+time);
            courseLog.setCourseClass(courseClass);
            courseLog.setCourseName(courseName);
            session.insert("com.tute.wjl.mapper.CourseLogMapper.insert",courseLog);
            session.commit();
            // 保存【组-记录id】
            System.out.println("创建分组：分组ID "+toId);
            courseLogMap.put(toId,courseLog.getId());
            groupMap.put(toId,cg);
            // ...记得把自己加入分组，，，
            message.setMessageType(Constants.CREATE_SUCCESS);
            ctx.writeAndFlush(message);
            groupWithUserNames.put(toId,new ArrayList<>());
        }else{
            message.setMessageType(Constants.ERROR);
            message.setContent("【"+courseClass+"】这个班正在上【"+courseClass+"】中。。。");
            ctx.writeAndFlush(message);
        }
//        addGroup(message,ctx);
    }

    // 销毁分组
    public void destroyGroup(Message message,ChannelHandlerContext ctx){
        String toId = message.getToId(); // toId是分组名
        ChannelGroup group = groupMap.get(toId);
        // 移除自己
        group.remove(ctx.channel());
        // 发送关闭信息
        group.writeAndFlush(message);
        group.close();
        groupMap.remove(toId);
        groupWithUserNames.remove(toId);
        // TODO 生成报告表格xsl
        int courseLogId = courseLogMap.get(toId);
        List<StuCourseLogInfo> stuCourseLogInfos = session.selectList("com.tute.wjl.mapper.StuCourseLogMapper.getStuCourseLogInfo",courseLogId);
        // 生成签到记录并上传
        String thisDate = DateUtil.formatDateTime(new Date());
        String fileName = toId+"-"+thisDate;
        fileName = ExcelUtil.export(stuCourseLogInfos,fileName);
        CourseLog log = new CourseLog();
        log.setId(courseLogId);
        log.setLogUrl(fileName);
        log.setEndTime(thisDate);
        // 结束开课记录
        session.update("com.tute.wjl.mapper.CourseLogMapper.updateStatus",log);
        session.commit();
        courseLogMap.remove(toId);
    }

    // 加入分组
    public void addGroup(Message message, ChannelHandlerContext ctx){
        String toId = message.getToId();
        String fromId = message.getFromId();
        ChannelId channelId = userMap.get(fromId);
        ChannelGroup cg = groupMap.get(toId);
        if (cg != null){
            // 将自己加入分组
            cg.add(NettyServer.group.find(channelId));
            // 返回加入成功的信息
            message.setMessageType(Constants.ADD_SUCCESS);
            // 把目前在组里的人(除了自己)发送过去
            message.setContent(groupWithUserNames.get(toId));
            String name = message.getFromName()+"("+fromId+")";
            groupWithUserNames.get(toId).add(name);
            // 更新登陆人数
            CourseLog courseLog = new CourseLog();
            courseLog.setAttendNumber(cg.size());
            // 获取的logId
            courseLog.setId(courseLogMap.get(toId));
            session.update("com.tute.wjl.mapper.CourseLogMapper.updateNumber",courseLog);
            session.commit();
            cg.writeAndFlush(message);
        }else{
            ctx.writeAndFlush(new Message("加入失败，暂时还没有开课哦～"));
        }
    }

    // 退出分组
    public void quitGroup(Message message,ChannelHandlerContext ctx){
        String toId = message.getToId();
        if(groupMap.containsKey(toId)){
            ChannelGroup cg = groupMap.get(toId);
            ChannelId channelId = userMap.get(message.getFromId());
            String name = message.getFromName()+"("+message.getFromId()+")";
            groupWithUserNames.get(toId).remove(name);
            groupMap.get(toId).remove(NettyServer.group.find(channelId));
            message.setContent(groupWithUserNames.get(toId));
            cg.writeAndFlush(message);
        }
    }
}
