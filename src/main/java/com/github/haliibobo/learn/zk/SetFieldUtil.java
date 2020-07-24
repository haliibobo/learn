package com.github.haliibobo.learn.zk;

import java.lang.reflect.Field;

public class SetFieldUtil {

    public static void setField(Object object, String fieldName, Object fieldValue) {
        Field field = null;
        try {
            field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, fieldValue);
            field.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setSuperField(Object object, String fieldName, Object fieldValue) {
        try {
            Field field = object.getClass().getSuperclass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, fieldValue);
            field.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
