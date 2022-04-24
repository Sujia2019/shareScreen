package com.tute.wjl.entity;

import lombok.Data;

// 学生签到记录
@Data
public class StuCourseLog {
    private int id;
    private String courseName;
    private String courseClass;
    private int logId;
    private String stuAccount;
    private String content;
    private String createTime;
}
