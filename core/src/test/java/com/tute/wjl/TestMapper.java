package com.tute.wjl;

import com.tute.wjl.mapper.CourseMapper;
import com.tute.wjl.utils.MybatisConf;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

//@Test
public class TestMapper {
    private CourseMapper courseMapper;

    @Test
    public void getByClass(){
        SqlSession session = MybatisConf.getSqlSession();
        session.selectList("getByClass","网络1801");
//        courseMapper.getByClass("网络1801");
    }
}
