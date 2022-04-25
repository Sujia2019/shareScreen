package com.tute.wjl.entity;

import lombok.Data;

@Data
public class CourseLog {
    private int id;
    private String logName;
    private String courseName;
    private String courseClass;
    private String createTime;
    private String teacherAccount;
    private String teacherName;
    private int attendNumber;
    private boolean isFinished;
    private String endTime; // 签到记录生成时间
    private String logUrl;
}
