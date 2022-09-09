package com.leon.framework.beans.context;

import com.leon.framework.beans.BeanDefinition;
import com.leon.framework.beans.MutablePropertyValues;
import com.leon.framework.beans.PropertyValue;
import com.leon.framework.beans.factory.support.BeanDefinitionRegistry;
import com.leon.framework.beans.factory.xml.XMLBeanDefinitionReader;
import com.leon.framework.beans.utils.StringUtils;

import java.lang.reflect.Method;

/**
 * IOC container concrete implement class
 * used to load the xml file
 */
public class ClassPathXMLApplicationContext extends AbstractApplicationContext {
    public ClassPathXMLApplicationContext(String configPath) {
        this.configPath = configPath;
        this.beanDefinitionReader = new XMLBeanDefinitionReader();
        try {
            this.refresh();
        } catch (Exception e) {

        }
    }

    // get the bean by bean name
    @Override
    public Object getBean(String name) throws Exception {
        Object obj = singletonObjects.get(name);
        if (obj != null) {
            return obj;
        }

        BeanDefinitionRegistry registry = beanDefinitionReader.getRegistry();
        BeanDefinition beanDefinition = registry.getBeanDefinition(name);
        String className = beanDefinition.getClassName();
        Class<?> clazz = Class.forName(className);
        Object beanObj = clazz.newInstance();
        // Dependency Injection, inject all the properties for this bean
        MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues) {
            String propertyName = propertyValue.getName();
            String value = propertyValue.getValue();
            String ref = propertyValue.getRef();
            if (ref != null & !ref.equals("")) {
                // get the dependency bean object
                Object bean = getBean(ref);
                //
                String methodName = StringUtils.getSetterMethodByFieldName(propertyName);
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if (method.getName().equals(methodName)) {
                        method.invoke(beanObj, bean);
                    }
                }
            }

            if (value != null && !value.equals("")) {
                String methodName = StringUtils.getSetterMethodByFieldName(propertyName);
                Method method = clazz.getMethod(methodName, String.class);
                method.invoke(beanObj, value);
            }
        }
        this.singletonObjects.put(name, beanObj);
        return null;
    }

    @Override
    public <T> T getBean(String name, Class<? extends T> clazz) throws Exception {
        Object bean = this.getBean(name);
        if (bean == null){
            return null;
        }
        return clazz.cast(bean);
    }
}
