package com.leon.framework.beans.factory;

/**
 * IOC container interface
 */
public interface BeanFactory {
    Object getBean(String name) throws Exception;
    <T> T getBean(String name, Class<? extends T> clazz) throws Exception;
}
