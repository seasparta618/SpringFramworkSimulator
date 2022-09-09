package com.leon.framework.beans.context;

import com.leon.framework.beans.factory.BeanFactory;

/**
 * define the immediately / non-lazy loading interface
 */
public interface ApplicationContext extends BeanFactory {
    void refresh() throws Exception;
}
