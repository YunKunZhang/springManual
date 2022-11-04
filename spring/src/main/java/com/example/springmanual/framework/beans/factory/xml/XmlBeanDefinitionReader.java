package com.example.springmanual.framework.beans.factory.xml;


import com.example.springmanual.framework.beans.BeanDefinition;
import com.example.springmanual.framework.beans.MutablePropertyValues;
import com.example.springmanual.framework.beans.PropertyValue;
import com.example.springmanual.framework.beans.factory.support.BeanDefinitionReader;
import com.example.springmanual.framework.beans.factory.support.BeanDefinitionRegistry;
import com.example.springmanual.framework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: XmlBeanDefinitionReader
 * @Description: 针对xml配置文件进行解析的类
 */
public class XmlBeanDefinitionReader implements BeanDefinitionReader {

    //声明注册表对象
    private BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader() {
        registry = new SimpleBeanDefinitionRegistry();
    }

    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    public void loadBeanDefinitions(String configLocation) throws Exception {
        //使用dom4j进行xml配置文件的解析
        SAXReader reader = new SAXReader();
        //获取类路径下的配置文件
        InputStream is = XmlBeanDefinitionReader.class.getClassLoader().getResourceAsStream(configLocation);
        Document document = reader.read(is);
        //根据Document对象获取根标签对象 (beans)
        Element rootElement = document.getRootElement();
        //获取根标签下所有的bean标签对象
        List<Element> beanElements = rootElement.elements("bean");
        //遍历集合
        for (Element beanElement : beanElements) {
            //获取id属性
            String id = beanElement.attributeValue("id");
            //获取class属性
            String className = beanElement.attributeValue("class");

            //将id属性和class属性封装到BeanDefinition对象中
            //1，创建BeanDefinition
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setId(id);
            beanDefinition.setClassName(className);

            //创建MutablePropertyValues对象
            MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();

            //获取bean标签下所有的property标签对象
            List<Element> propertyElements = beanElement.elements("property");
            for (Element propertyElement : propertyElements) {
                String name = propertyElement.attributeValue("name");
                String ref = propertyElement.attributeValue("ref");
                String value = propertyElement.attributeValue("value");
                PropertyValue propertyValue = new PropertyValue(name, ref, value);
                mutablePropertyValues.addPropertyValue(propertyValue);
            }
            //将mutablePropertyValues对象封装到BeanDefinition对象中
            beanDefinition.setPropertyValues(mutablePropertyValues);

            //将beanDefinition对象注册到注册表中
            registry.registerBeanDefinition(id, beanDefinition);
        }
    }

    public void parseAspect(String configLocation) throws Exception {
        //使用dom4j进行xml配置文件的解析
        SAXReader reader = new SAXReader();
        //获取类路径下的配置文件
        InputStream is = XmlBeanDefinitionReader.class.getClassLoader().getResourceAsStream(configLocation);
        Document document = reader.read(is);
        //根据Document对象获取根标签对象 (beans)
        Element rootElement = document.getRootElement();
        //获取跟标签下的aspect标签对象
        List<Element> aopConfig = rootElement.elements("aspect");
        //遍历集合
        for (Element element : aopConfig) {
            //获取ref属性
            String ref = element.attributeValue("ref");

            //获取切入点
            Element pointcut = element.element("pointcut");
            String expression = pointcut.attributeValue("expression");

            //通过ref取bean容器中找加载的切面类
            BeanDefinition beanDefinition = registry.getBeanDefinition(ref);
            String className = beanDefinition.getClassName();
            //通过反射创建对象
            Class<?> clazz = Class.forName(className);

            //加载所有通知
            Object[] advice = loadAdvice(element, pointcut.attributeValue("id"), clazz);
            advice[5] = clazz.newInstance();
            registry.registerAdvice(expression, advice);
        }
    }

    //加载通知
    private Object[] loadAdvice(Element aspect, String pointCutId, Class clazz) {
        Object[] res = new Object[6];
        Element around = aspect.element("around");
        Element before = aspect.element("before");
        Element after = aspect.element("after");
//        Element afterReturning = aspect.element("after-returning");
//        Element afterThrowing = aspect.element("after-throwing");
        for (Method method : clazz.getMethods()) {
            if (around != null && pointCutId.equals(around.attributeValue("pointcut-ref")) &&
                    method.getName().equals(around.attributeValue("method"))) {
                res[0] = method;
            }
            if (before != null && pointCutId.equals(before.attributeValue("pointcut-ref")) &&
                    method.getName().equals(before.attributeValue("method"))) {
                res[1] = method;
            }
            if (after != null && pointCutId.equals(after.attributeValue("pointcut-ref")) &&
                    method.getName().equals(after.attributeValue("method"))) {
                res[2] = method;
            }
//            res.add(afterReturning);
//            res.add(afterThrowing);
        }
        return res;
    }
}
