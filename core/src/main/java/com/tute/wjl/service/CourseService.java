package com.tute.wjl.service;

import com.tute.wjl.entity.Course;
import com.tute.wjl.entity.Message;
import com.tute.wjl.entity.User;
import com.tute.wjl.utils.Constants;
import com.tute.wjl.utils.MybatisConf;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseService {
    private static CourseService instance;
    public static Map<String, Course> courseMap = new HashMap<>();
    private SqlSession session = MybatisConf.getSqlSession();


    public static CourseService getInstance() {
        if(instance==null) {//先判断是否为null 后上锁进行初始化
            synchronized (CourseService.class) {
                if (instance == null)//将对象上锁之后再次判断 是否有别的线程初始化了
                    instance = new CourseService();
            }
        }
        return instance;
    }

    public void getCourseByClass(Message message){
        message.setToId(Constants.COURSE_CLASS_SUCCESS);
        List<Course> res = session.selectList("com.tute.wjl.mapper.CourseMapper.getByClass",message.getContent());
        message.setContent(res);

    }

    public void getCourseByTeacher(Message message){
        message.setToId(Constants.COURSE_TEACHER_SUCCESS);
        System.out.println(message);
        List<Course> res = session.selectList("com.tute.wjl.mapper.CourseMapper.getByTeacher",message.getContent());
        message.setContent(res);
    }

    // 模糊查询
    public void getCourseByName(Message message){
        message.setToId(Constants.COURSE_SEARCH_SUCCESS);
        List<Course> res = session.selectList("com.tute.wjl.mapper.CourseMapper.getByName",message.getContent());
        message.setContent(res);
    }

    public void insert(Message message){
        if( session.insert("com.tute.wjl.mapper.CourseMapper.insert",message.getContent())>0){
            session.commit();
            message.setToId(Constants.COURSE_NEW_SUCCESS);
        }else{
            message = new Message("新建课程失败");
        }
    }

    public void update(Message message){
        if( session.update("com.tute.wjl.mapper.CourseMapper.update",message.getContent())>0){
            session.commit();
            message.setToId(Constants.COURSE_UPDATE_SUCCESS);
        }else{
            message = new Message("更新课程失败");
        }
    }

    public void delete(Message message){
        if( session.delete("com.tute.wjl.mapper.CourseMapper.delete",message.getContent())>0){
            session.commit();
            message.setToId(Constants.COURSE_DELETE_SUCCESS);
        }else{
            message = new Message("删除课程失败");
        }
    }
}
