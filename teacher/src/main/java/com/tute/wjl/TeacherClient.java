package com.tute.wjl;

import com.tute.wjl.entity.Message;
import com.tute.wjl.entity.User;
import com.tute.wjl.net.NettyClient;
import com.tute.wjl.service.PictureService;
import com.tute.wjl.ui.ShareFrame;
import com.tute.wjl.utils.Constants;

public class TeacherClient {
    public static void main(String[] args) throws Exception {
        ClientApplication client = new ClientApplication();
        client.start("localhost",8888);

//        NettyClient client = new NettyClient(new ShareFrame());
//        client.init();

        // 登陆
//        User user = new User();
//        user.setUserAccount("teacher");
//        user.setTeacher(true);
//        Message m1 = new Message();
//        m1.setFromId("teacher");
//        m1.setMessageType(Constants.USER);
//        m1.setContent(user);
//        ClientApplication.send(m1);
//
//
//        Message start = new Message();
//        // 开始上课,创建分组
//        start.setFromId("teacher");
//        start.setMessageType(Constants.CREATE);
//        // 上课班级
//        start.setToId("123");
//        ClientApplication.send(start);
//
//        // 停了三秒
//        Thread.sleep(3000);

        // 开始共享屏幕
//        while (true){
//            Message message = new Message();
//            message.setMessageType(Constants.PICTURE);
//            message.setToId("123");
//            message.setFromId("teacher");
//            PictureService.getInstance().sendPicByte(message);
//            client.send(message);
//            // 30帧
//            // Thread.sleep(33);
//            // 60帧  17
//            Thread.sleep(100);
//        }
    }
}
