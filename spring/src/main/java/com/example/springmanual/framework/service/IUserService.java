package com.example.springmanual.framework.service;


import com.example.springmanual.framework.entity.User;

import java.util.List;

/**
 * @Author:ZhangYuKun
 * @Date:2022/11/3 16:07
 */
public interface IUserService {

    /**
     * find user list.
     *
     * @return user list
     */
    List<User> findUserList();

    User get(String name);

    /**
     * add user
     */
    void addUser();
}
