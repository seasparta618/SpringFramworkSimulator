package com.leon.framework.beans.context;

import com.leon.framework.beans.BeanDefinition;
import com.leon.framework.beans.factory.support.BeanDefinitionReader;
import com.leon.framework.beans.factory.support.BeanDefinitionRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * implement class of the application context
 * used to non-lazy / immediately loading
 */
public abstract class AbstractApplicationContext implements ApplicationContext{
    protected BeanDefinitionReader beanDefinitionReader;
    protected Map<String, Object> singletonObjects = new HashMap<>();
    protected String configPath;

    @Override
    public void refresh() throws Exception {
        this.beanDefinitionReader.loadBeanDefinitions(configPath);
        finishBeanInitialization();
    }

    // initialize the beans
    private void finishBeanInitialization() throws Exception{
        BeanDefinitionRegistry registry = this.beanDefinitionReader.getRegistry();
        String[] beanDefinitionNames = registry.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            this.getBean(beanDefinitionName);
        }
    }
}
