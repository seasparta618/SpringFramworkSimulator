package com.leon.framework.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * this is for store and management of multiple property value
 */
public class MutablePropertyValues implements Iterable<PropertyValue> {

    private final List<PropertyValue> propertyValueList;

    public MutablePropertyValues() {
        propertyValueList = new ArrayList<>();
    }

    public MutablePropertyValues(List<PropertyValue> propertyValueList) {
        if (propertyValueList == null) {
            this.propertyValueList = new ArrayList<>();
        } else {
            this.propertyValueList = propertyValueList;
        }
    }

    // get all property values object, return as array format
    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    // find propertyValue by property.name
    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue propertyValue : this.propertyValueList) {
            if (propertyValue.getName().equals(propertyName)) {
                return propertyValue;
            }
        }
        return null;
    }

    // check the propertyValueList is nullable
    public boolean isEmpty() {
        return this.propertyValueList.isEmpty();
    }

    // add the property value into property value list
    public MutablePropertyValues addPropertyValue(PropertyValue propertyValue) {
        for (int i = 0; i < propertyValueList.size(); i++) {
            PropertyValue p = propertyValueList.get(i);
            if (p.getName().equals(propertyValue.getName())) {
                propertyValueList.set(i, propertyValue);
                return this;
            }
        }
        this.propertyValueList.add(propertyValue);
        return this;
    }

    // check if the property by name exists
    public boolean contains(String propertyName){
        return getPropertyValue(propertyName) != null;
    }

    // get the iterator objects
    @Override
    public Iterator<PropertyValue> iterator() {
        return this.propertyValueList.iterator();
    }
}
