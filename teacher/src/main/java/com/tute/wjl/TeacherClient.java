package com.tute.wjl;

import com.tute.wjl.entity.Message;
import com.tute.wjl.net.NettyClient;
import com.tute.wjl.net.service.PictureService;
import com.tute.wjl.ui.Client;
import com.tute.wjl.utils.Constants;

public class TeacherClient {
    public static void main(String[] args) throws Exception {
        NettyClient client = new NettyClient(new Client());
        client.init("localhost",8888);
        while (true){
            Message message = new Message();
            message.setMessageType(Constants.PICTURE);
            PictureService.getInstance().sendPicByte(message);
            client.send(message);
            // 30帧
            // Thread.sleep(33);
            // 60帧
            Thread.sleep(25);
        }
    }
}
