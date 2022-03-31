package com.tute.wjl.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private int id;
    private String userAccount;
    private String userName;
    private String trueName;
    private String userPwd;
    private String userClass;
    private String content;
    private int age;
    private boolean isTeacher;

}
