package com.tute.wjl.service;

import com.tute.wjl.entity.Message;
import com.tute.wjl.entity.UpdateDTO;
import com.tute.wjl.entity.User;
import com.tute.wjl.utils.Constants;
import com.tute.wjl.utils.MybatisConf;
import io.netty.channel.ChannelHandlerContext;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private static UserService instance;
    public static Map<String,User> users = new HashMap<>();
    private SqlSession session = MybatisConf.getSqlSession();

    static {
        users.put("123",createUser("123","123","网络工程",false));
        users.put("test",createUser("test","test","网络工程",false));
        users.put("student",createUser("student","student","网络工程",false));
        users.put("test2",createUser("test2","test2","软件工程",false));
        users.put("teacher",createUser("teacher","teacher","网络工程",true));
    }

    public static UserService getInstance() {
        if(instance==null) {//先判断是否为null 后上锁进行初始化
            synchronized (UserService.class) {
                if (instance == null)//将对象上锁之后再次判断 是否有别的线程初始化了
                    instance = new UserService();
            }
        }
        return instance;
    }

    public void doLogin(Message message,ChannelHandlerContext ctx){
        User user = (User) message.getContent();
        user = session.selectOne("login",user);
        if(user!=null){
            loginSuccess(ctx,user,Constants.LOGIN_SUCCESS);
            message.setContent(user);
        }else{
            Message res = new Message("登陆失败,用户名或密码错误");
            ctx.writeAndFlush(res);
        }

    }

    private void loginSuccess(ChannelHandlerContext ctx, User user,String type) {
        GroupService.userMap.put(user.getUserAccount(),ctx.channel().id());
        Message res = new Message();
        res.setContent(user);
        res.setMessageType(Constants.USER);
        res.setToId(type);
        res.setFromId("SERVER");
        // 发送用户登陆信息
        ctx.writeAndFlush(res);
    }

    public void doRegister(Message message, ChannelHandlerContext ctx){
        User user = (User) message.getContent();
        if(session.selectOne("getCountByAccount",user.getUserAccount()).equals(0)){
            createUser(user);
            if(session.insert("com.tute.wjl.mapper.UserMapper.insert",user)>0){
                session.commit();
                loginSuccess(ctx,user,Constants.REGISTER_SUCCESS);
            }
        }else{
            Message res = new Message("注册失败，账号已存在");
            ctx.writeAndFlush(res);
        }
    }


    public void updateUser(Message message,ChannelHandlerContext ctx){
        User user = (User) message.getContent();
        String beforeName = session.selectOne("com.tute.wjl.mapper.UserMapper.getByAccount",user.getUserAccount());
        UpdateDTO update = new UpdateDTO();
        update.setAfterName(user.getTrueName());
        update.setBeforeName(beforeName);
        session.update("com.tute.wjl.mapper.CourseMapper.updateTeacherName",update);
        session.commit();
        if(session.update("com.tute.wjl.mapper.UserMapper.update",user)>0){
            session.commit();
            message.setFromId("SERVER");
            message.setToId(Constants.USER_UPDATE_SUCCESS);
            ctx.writeAndFlush(message);
        }else{
            Message res = new Message("更新失败");
            ctx.writeAndFlush(res);
        }
    }


    private static User createUser(String userAccount,String trueName,String userClass,boolean isTeacher){
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserName(trueName);
        user.setUserPwd("123");
        user.setAge(20);
        user.setTrueName(trueName);
        user.setContent("备注信息");
        user.setUserClass(userClass);
        user.setTeacher(isTeacher);
        return user;
    }

    private User createUser(User user){
        user.setAge(20);
        user.setTrueName("待完善");
        user.setContent("待完善");
        user.setUserClass("待完善");
        user.setUserName("用户"+user.getUserAccount());
        user.setUserClass("待完善");
        return user;
    }
}
