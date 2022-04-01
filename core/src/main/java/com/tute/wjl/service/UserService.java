package com.tute.wjl.service;

import com.tute.wjl.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private static UserService instance;
    public static Map<String,User> users = new HashMap<>();

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

    public User doLogin(User user){
        String userAccount = user.getUserAccount();
        String userPwd = user.getUserPwd();
        if(users.containsKey(userAccount)){
            User src = users.get(userAccount);
            if(src != null){
                if(src.isTeacher()!=user.isTeacher()){
                    return null;
                }
                if(src.getUserPwd().equals(userPwd)){
                    return src;
                }
            }
        }
        return null;
    }

    public User doRegister(User user){
        String userAccount = user.getUserAccount();
        if(users.containsKey(userAccount)){
            return null;
        }
        createUser(user);
        users.put(userAccount,user);
        return user;
    }


    public User updateUser(User user){
        String userAccount = user.getUserAccount();
        if(users.get(userAccount)!=null){
            users.put(userAccount,user);
        }
        return user;
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

    private static User createUser(User user){
        user.setAge(20);
        user.setTrueName("待完善");
        user.setContent("待完善");
        user.setUserClass("待完善");
        user.setUserName("待完善");
        return user;
    }
}
