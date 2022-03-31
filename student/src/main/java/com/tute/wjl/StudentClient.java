package com.tute.wjl;

import com.tute.wjl.entity.Message;
import com.tute.wjl.entity.User;
import com.tute.wjl.utils.Constants;

public class StudentClient {
    public static void main(String[] args) throws Exception {
        ClientApplication client = new ClientApplication();
        client.start("localhost",8888);
        // 登陆
//        User user = new User();
//        user.setUserAccount("student");
//        user.setTeacher(false);
//        Message m1 = new Message();
//        m1.setFromId("student");
//        m1.setMessageType(Constants.USER);
//        m1.setContent(user);
//        client.send(m1);
//
//        // 加入班级
//        Message message = new Message();
//        message.setMessageType(Constants.ADD);
//        message.setFromId("student");
//        message.setToId("123");
//        client.send(message);
    }
}
