package com.example.springmanual.framework.service.impl;


import com.example.springmanual.framework.dao.UserDao;
import com.example.springmanual.framework.entity.User;
import com.example.springmanual.framework.service.IUserService;

import java.util.Collections;
import java.util.List;

/**
 * @Author:ZhangYuKun
 * @Date:2022/11/3 16:08
 */
public class UserServiceImpl implements IUserService {

    private UserDao userDao;

    @Override
    public List<User> findUserList() {
        return Collections.singletonList(new User("pdai", 18));
    }

    @Override
    public User get(String name) {
        System.out.println("service方法:get");
        return userDao.get();
    }

    @Override
    public void addUser() {
        userDao.addUser();
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
