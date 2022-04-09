package com.tute.wjl.context;

import com.tute.wjl.entity.Message;
import com.tute.wjl.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class DataContext {
    private User user ;
    private String shareGroupName;
    private String teacherId;
    private boolean isTeacher;
    private List data;
    public static volatile boolean isReceive = false;
    public static volatile boolean needStop = false;


    public Message initMessage(String messageType){
        Message message = new Message();
        message.setFromId(user.getUserAccount());
        message.setFromName(user.getTrueName());
        message.setMessageType(messageType);
        return message;
    }
}
