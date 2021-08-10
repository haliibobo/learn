package com.github.haliibobo.learn.annotation;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigBeanFactory;
import com.typesafe.config.ConfigFactory;

import java.lang.reflect.Field;

public class AnnService {
    private Config config;
    @Parameter(simpleClassNamePrefix = false)
    private String  userName;
    {
        config = ConfigFactory.load("ann.conf");
    }
    public static void main(String[] args) throws IllegalAccessException {
        System.out.println(123);
        AnnService annService = new AnnService();
        annService.injectParam(annService);
        System.out.println(annService.userName);
    }


    private void injectParam (Object obj) throws IllegalAccessException {

        for (Field field :obj.getClass().getDeclaredFields()){
            Parameter parameter = field.getAnnotation(Parameter.class);
            if (parameter == null) {
                continue;
            }
            String prefix = getParameterPrefix(parameter,  obj.getClass());
            String key = getParameterPath(parameter, field);
            String paramFullKey = prefix + key;
            String value =getString(paramFullKey);
            field.setAccessible(true);
            field.set(obj, value);
        }

    }
    /**
     * be private later.
     */
    @SuppressWarnings({"rawtypes"})
    public String getString( String path) {
        return config.getString(path);
    }

    private static String getParameterPrefix(Parameter parameter, Class type) {
        StringBuilder builder = new StringBuilder();
        if (parameter.simpleClassNamePrefix()) {
            builder.append(type.getSimpleName()).append(".");
        }
        return builder.toString();
    }

    private static String getParameterPath(Parameter parameter, Field field) {
        String name = parameter.nameOverride();
        if (name.isEmpty()) {
            name = field.getName();
        }
        return name;
    }
}
