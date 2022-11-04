package com.example.springmanual.framework.beans.factory;

import java.util.List;

/**
 * @version v1.0
 * @ClassName: BeanFactory
 * @Description: IOC容器父接口
 */
public interface BeanFactory {

    List<Object> getAllBeans() throws Exception;

    Object getBean(String name) throws Exception;

    <T> T getBean(String name, Class<? extends T> clazz) throws Exception;
}
