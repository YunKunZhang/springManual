package com.example.springmanual.framework.dao.impl;


import com.example.springmanual.framework.dao.UserDao;
import com.example.springmanual.framework.entity.User;

/**
 * @Author:ZhangYuKun
 * @Date:2022/11/3 16:19
 */
public class UserDaoImpl implements UserDao {

    public void addUser() {
        System.out.println("dao方法：addUser");
    }

    @Override
    public User get() {
        System.out.println("dao方法：get");
        return new User("张三", 18);
    }
}
