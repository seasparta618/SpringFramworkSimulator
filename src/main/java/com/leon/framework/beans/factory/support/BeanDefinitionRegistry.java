package com.leon.framework.beans.factory.support;

import com.leon.framework.beans.BeanDefinition;

/**
 * simulation of the bean registry
 */
public interface BeanDefinitionRegistry {

    // register the BeanDefinition into the registry
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    // remove the BeanDefinition from the registry
    void removeBeanDefinition(String beanName) throws Exception;

    // get the beanDefinition from the registry by beanName
    BeanDefinition getBeanDefinition(String beanName) throws Exception;

    // check if the registry contains the BeanDefinition by bean name
    boolean containsBeanDefinition(String beanName);

    // get the count of all BeanDefinition in the registry
    int getBeanDefinitionCount();

    // get all the BeanDefinition name
    String[] getBeanDefinitionNames();
}
