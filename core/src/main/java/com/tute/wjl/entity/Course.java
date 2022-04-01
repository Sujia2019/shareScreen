package com.tute.wjl.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Course implements Serializable {
    private int id;
    private String courseName;
    private String courseContent;
    private String courseTeacher;
    private String courseClass;
    private int courseHours;
    private String courseTime;
}
