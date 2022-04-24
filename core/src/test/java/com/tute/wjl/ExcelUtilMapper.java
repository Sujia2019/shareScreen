package com.tute.wjl;

import com.tute.wjl.entity.CourseLog;
import com.tute.wjl.entity.UpdateDTO;
import com.tute.wjl.entity.User;
import com.tute.wjl.mapper.CourseLogMapper;
import com.tute.wjl.mapper.CourseMapper;
import com.tute.wjl.mapper.UserMapper;
import com.tute.wjl.utils.MybatisConf;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

//@ExcelUtil
public class ExcelUtilMapper {
    private CourseMapper courseMapper;
    private UserMapper userMapper;
    private CourseLogMapper courseLogMapper;

    @Test
    public void getByClass() {
        SqlSession session = MybatisConf.getSqlSession();
        session.selectList("getByClass", "网络1801");
//        courseMapper.getByClass("网络1801");
    }

    @Test
    public void updateTeacherName() {
//        User user = new User();
//        user.setUserAccount("teacher");
//        user.setUserName("teacher");
//        user.setUserPwd("123");
//        user.setTrueName("teacher11");
//        user.setAge(32);
//        user.setContent("计算机老师123");
//        SqlSession session = MybatisConf.getSqlSession();
//        String beforeName = session.selectOne("com.tute.wjl.mapper.UserMapper.getByAccount", user.getUserAccount());
//        UpdateDTO update = new UpdateDTO();
//        update.setAfterName(user.getTrueName());
//        update.setBeforeName(beforeName);
//        session.update("com.tute.wjl.mapper.CourseMapper.updateTeacherName", update);
//        session.update("com.tute.wjl.mapper.UserMapper.update", user);
//        session.commit();
    }
    @Test
    public void insertCourseLog(){
        CourseLog courseLog = new CourseLog();
        courseLog.setCourseClass("xxxclass");
        courseLog.setCourseName("xxxxxxxxName");
        courseLog.setLogName("今天今天");
        courseLog.setTeacherName("teacher");
        courseLog.setTeacherAccount("teacher");
        SqlSession session = MybatisConf.getSqlSession();
        session.insert("com.tute.wjl.mapper.CourseLogMapper.insert",courseLog);
        session.commit();
        System.out.println(courseLog);
    }
}
