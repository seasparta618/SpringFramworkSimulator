package com.leon.framework.beans.utils;

public class StringUtils {
    private StringUtils() {

    }

    public static String getSetterMethodByFieldName(String fieldName) {
        String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        return methodName;
    }
}
