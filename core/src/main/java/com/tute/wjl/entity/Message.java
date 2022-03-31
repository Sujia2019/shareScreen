package com.tute.wjl.entity;

import com.tute.wjl.utils.Constants;
import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {
    private int length;             // 总长度
    // 用于判定 图片/声音/群发/私聊/控制请求/开课申请/加入课程
    private String messageType;
    private String fromId;               // userAccount
    private String fromName;             // userName
    private String toId;                 // userAccount或者groupId对应的班级 或者目的
    private Object content;              // 消息内容
    private byte[] fileContent;          // 文件内容

    public Message(){

    }

    public Message (String errorMsg){
        this.fromId = Constants.SERVER;
        this.messageType = Constants.ERROR;
        this.content = errorMsg;
    }

}
