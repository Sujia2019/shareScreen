package com.tute.wjl.service;

import com.tute.wjl.entity.Message;

public class VoiceService {
    private static VoiceService instance;
    public static VoiceService getInstance() {
        if(instance==null) {//先判断是否为null 后上锁进行初始化
            synchronized (VoiceService.class) {
                if (instance == null)//将对象上锁之后再次判断 是否有别的线程初始化了
                    instance = new VoiceService();
            }
        }
        return instance;
    }

    public void sendVoice(Message message){

    }

    public void playVoice(Message message){

    }
}
