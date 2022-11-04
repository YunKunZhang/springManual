package com.example.springmanual.controller;

import com.example.springmanual.annotation.MyAutowired;
import com.example.springmanual.annotation.MyController;
import com.example.springmanual.annotation.MyRequestMapping;
import com.example.springmanual.annotation.MyRequestParam;
import com.example.springmanual.framework.entity.User;
import com.example.springmanual.framework.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:ZhangYuKun
 * @Date:2022/11/1 11:30
 */
@MyController
@MyRequestMapping("/demo")
public class DemoController {

    @MyAutowired
    public IUserService userService;

    @MyRequestMapping("/query")
    public void query(HttpServletRequest req, HttpServletResponse resp,
                      @MyRequestParam("name") String name) {
        System.out.println("controller方法:query");
        User result = userService.get(name);
        try {
            resp.getWriter().write(result.getAge() + " ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @MyRequestMapping("/add")
    public void add(HttpServletRequest req, HttpServletResponse resp,
                    @MyRequestParam("a") Integer a, @MyRequestParam("b") Integer b) {
        try {
            resp.getWriter().write(a + "+" + b + "=" + (a + b));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @MyRequestMapping("/remove")
    public void remove(HttpServletRequest req, HttpServletResponse resp,
                       @MyRequestParam("id") Integer id) {
        System.out.println("删除id为：" + id + "的用户");
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
