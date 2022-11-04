package com.example.springmanual.framework.beans.factory.support;


import com.example.springmanual.framework.beans.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @version v1.0
 * @ClassName: SimpleBeanDefinitionRegistry
 * @Description: 注册表接口的子实现类
 */
public class SimpleBeanDefinitionRegistry implements BeanDefinitionRegistry {

    //定义一个容器，用来存储BeanDefinition对象
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();

    //定义一个容器，用来存储expression和通知
    private Map<String, Object[]> adviceMap = new HashMap<String, Object[]>();

    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    public void removeBeanDefinition(String beanName) throws Exception {
        beanDefinitionMap.remove(beanName);
    }

    public BeanDefinition getBeanDefinition(String beanName) throws Exception {
        return beanDefinitionMap.get(beanName);
    }

    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    public int getBeanDefinitionCount() {
        return beanDefinitionMap.size();
    }

    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    public void registerAdvice(String expression, Object[] ad) {
        adviceMap.put(expression, ad);
    }


    public Object[] isEnhanced(Class clazz) {
        String name = clazz.getSimpleName();
        for (Map.Entry<String, Object[]> entry : adviceMap.entrySet()) {
            String expression = entry.getKey();
            if (expression.contains(name)) {
                return entry.getValue();
            }
        }
        return null;
    }
}
