package com.example.springmanual.framework.context;


import com.example.springmanual.framework.beans.factory.BeanFactory;

/**
 * @version v1.0
 * @ClassName: ApplicationContext
 * @Description: 定义非延时加载功能
 */
public interface ApplicationContext extends BeanFactory {

    void refresh() throws Exception;
}
