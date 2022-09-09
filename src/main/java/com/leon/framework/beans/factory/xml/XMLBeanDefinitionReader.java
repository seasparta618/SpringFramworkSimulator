package com.leon.framework.beans.factory.xml;


import com.leon.framework.beans.BeanDefinition;
import com.leon.framework.beans.MutablePropertyValues;
import com.leon.framework.beans.PropertyValue;
import com.leon.framework.beans.factory.support.BeanDefinitionReader;
import com.leon.framework.beans.factory.support.BeanDefinitionRegistry;
import com.leon.framework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * implement class of BeanDefinitionReader
 * used to read from the xml config file and load the BeanDefinitions
 */
public class XMLBeanDefinitionReader implements BeanDefinitionReader {

    private BeanDefinitionRegistry registry;

    public XMLBeanDefinitionReader() {
        registry = new SimpleBeanDefinitionRegistry();
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return this.registry;
    }

    /**
     * load the bean definitions from the xml
     * use dom4j to analyze the xml
     * @param configPath
     * @throws Exception
     */
    @Override
    public void loadBeanDefinitions(String configPath) throws Exception {
        SAXReader saxReader = new SAXReader();
        InputStream resourceAsStream = XMLBeanDefinitionReader.class.getClassLoader().getResourceAsStream(configPath);
        Document document = saxReader.read(resourceAsStream);
        Element rootElement = document.getRootElement();
        List<Element> beanElements = rootElement.elements("bean");
        for (Element beanElement : beanElements) {
            String id = beanElement.attributeValue("id");
            String className = beanElement.attributeValue("class");
            // create the BeanDefinitions
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setId(id);
            beanDefinition.setClassName(className);

            MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();

            List<Element> propertyElements = beanElement.elements("property");
            for (Element propertyElement : propertyElements) {
                String name = propertyElement.attributeValue("name");
                String ref = propertyElement.attributeValue("ref");
                String value = propertyElement.attributeValue("value");
                PropertyValue propertyValue = new PropertyValue.Builder()
                        .value(value)
                        .ref(ref)
                        .name(name)
                        .build();
                mutablePropertyValues.addPropertyValue(propertyValue);
            }
            beanDefinition.setPropertyValues(mutablePropertyValues);
            this.registry.registerBeanDefinition(id, beanDefinition);
        }
    }
}
