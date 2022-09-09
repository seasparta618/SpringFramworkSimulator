package com.leon.framework.beans.factory.support;

/**
 * analyzing the config files
 * interface just define the abstract methods
 */
public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();

    void loadBeanDefinitions(String configPath) throws Exception;
}
