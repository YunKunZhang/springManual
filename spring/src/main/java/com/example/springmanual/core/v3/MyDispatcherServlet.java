package com.example.springmanual.core.v3;

import com.example.springmanual.annotation.MyController;
import com.example.springmanual.annotation.MyRequestMapping;
import com.example.springmanual.controller.DemoController;
import com.example.springmanual.framework.context.support.AbstractApplicationContext;
import com.example.springmanual.framework.context.support.ClassPathXmlApplicationContext;
import com.example.springmanual.framework.service.IUserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ZhangYuKun
 * @Date:2022/11/4 10:07
 */
public class MyDispatcherServlet extends HttpServlet {

    //上下文
    AbstractApplicationContext context;

    //保存url和Method的对应关系
    private Map<String, Method> handlerMapping = new HashMap<String, Method>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Exception" + Arrays.toString(e.getStackTrace()));
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String url = req.getRequestURI();
        System.out.println(url);
        if (!this.handlerMapping.containsKey(url)) {
            resp.getWriter().write("404 Not Found!");
            return;
        }
        Method method = this.handlerMapping.get(url);
        Map<String, String[]> params = req.getParameterMap();
        Object bean = context.getBean(toLowerFirstCase(method.getDeclaringClass().getSimpleName()));
        try {
            method.invoke(bean, req, resp, params.get("name")[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(ServletConfig config) {
        try {
            //加载配置文件
            doLoadConfig(config.getInitParameter("contextConfigLocation"));
            //初始化HandlerMapping
            initHandlerMapping();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //初始化url和Method的一对一关系
    private void initHandlerMapping() throws Exception {
        List<Object> allBeans = context.getAllBeans();
        if (allBeans.isEmpty()) {
            return;
        }
        for (Object bean : allBeans) {
            Class<?> clazz = bean.getClass();
            if (!clazz.isAnnotationPresent(MyController.class)) {
                continue;
            }
            //保存写在类上面的@RequestMapping("/demo")
            String baseUrl = "";
            if (clazz.isAnnotationPresent(MyRequestMapping.class)) {
                MyRequestMapping requestMapping = clazz.getAnnotation(MyRequestMapping.class);
                baseUrl = requestMapping.value();
            }
            //默认获取所有的public类型的方法
            for (Method method : clazz.getMethods()) {
                if (!method.isAnnotationPresent(MyRequestMapping.class)) {
                    continue;
                }
                MyRequestMapping requestMapping = method.getAnnotation(MyRequestMapping.class);
                //优化
                String url = ("/" + baseUrl + "/" + requestMapping.value()).replaceAll("/+", "/");
                handlerMapping.put(url, method);
                // /demo/query
                System.out.println("Mapped:" + url + "," + method);
            }
        }
    }

    private void doLoadConfig(String contextConfigLocation) {
        try {
            context = new ClassPathXmlApplicationContext(contextConfigLocation);
            System.out.println("加载配置完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //将类名首字母改为小写
    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        //之所以要做加法，是因为大小写字母的ASCII码差32
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
