package com.tute.wjl.mapper;

import com.tute.wjl.entity.StuCourseLog;
import com.tute.wjl.entity.StuCourseLogInfo;

import java.util.List;

public interface StuCourseMapper {
    // 根据学生账号和课程记录id查看学生签到记录
    public StuCourseLog getByStu(StuCourseLog stuCourseLog);

    // 根据课程名字查看学生签到记录
    public List<StuCourseLog> getByCourse(String courseName);

    // 根据课程记录的名称查看所有学生的签到记录
    public List<StuCourseLog> getByLogName(String logName);

    public int insert(StuCourseLog stuCourseLog);

    public List<StuCourseLogInfo> getStuCourseLogInfo(int logId);
}
