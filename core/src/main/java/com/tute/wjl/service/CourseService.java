package com.tute.wjl.service;

import com.tute.wjl.entity.Course;

import java.util.HashMap;
import java.util.Map;

public class CourseService {
    private static CourseService instance;
    public static Map<String, Course> courseMap = new HashMap<>();

    public static CourseService getInstance() {
        if(instance==null) {//先判断是否为null 后上锁进行初始化
            synchronized (CourseService.class) {
                if (instance == null)//将对象上锁之后再次判断 是否有别的线程初始化了
                    instance = new CourseService();
            }
        }
        return instance;
    }
}
