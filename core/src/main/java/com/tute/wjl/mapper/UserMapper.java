package com.tute.wjl.mapper;

import com.tute.wjl.entity.User;

public interface UserMapper {
    public int insert(User user);

    public User login(User user);

    public int update(User user);

    public int getCountByAccount(String account);

}
