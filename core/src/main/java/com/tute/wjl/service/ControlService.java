package com.tute.wjl.service;


public class ControlService {
    private static ControlService instance;
    // 双检锁单例模型
    public static ControlService getInstance() {
        if(instance==null) {//先判断是否为null 后上锁进行初始化
            synchronized (ControlService.class) {
                if (instance == null)//将对象上锁之后再次判断 是否有别的线程初始化了
                    instance = new ControlService();
            }
        }
        return instance;
    }


}
