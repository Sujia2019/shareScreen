package com.tute.wjl.mapper;

import com.tute.wjl.entity.Course;

import java.util.List;

public interface CourseMapper {
    public List<Course> getByClass(String className);

    public List<Course> getByTeacher(String teacherName);

    public List<Course> getByName(String courseName);

    public int insert(Course course);

    public int update(Course course);

    public int delete(int id);
}
