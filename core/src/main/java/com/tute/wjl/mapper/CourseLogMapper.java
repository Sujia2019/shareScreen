package com.tute.wjl.mapper;

import com.tute.wjl.entity.CourseLog;

import java.util.List;

public interface CourseLogMapper {
    public List<CourseLog> getByClass(String courseClass);

    public List<CourseLog> getByTeacher(String teacherName);

    public List<CourseLog> getByName(String courseName);

    public int insert(CourseLog courseLog);

    public int updateStatus(String courseClass);

    public int delete(int id);

    // 查询是否有未结束的课程
    public CourseLog getClassStatus(String courseClass);
}
