package com.tute.wjl.context;

import com.tute.wjl.entity.Message;
import com.tute.wjl.entity.User;
import lombok.Data;

@Data
public class DataContext {
    private User user ;
    private String shareGroupName;
    private String teacherId;
    public static volatile boolean needStop = false;


    public Message initMessage(String messageType){
        Message message = new Message();
        message.setFromId(user.getUserAccount());
        message.setMessageType(messageType);
        return message;
    }
}
