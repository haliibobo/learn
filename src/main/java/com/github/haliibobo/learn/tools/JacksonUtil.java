package com.github.haliibobo.learn.tools;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Set;


public class JacksonUtil {
    private static final Log log = LogFactory.getLog(JacksonUtil.class);
    private static ObjectMapper mapper = new ObjectMapper();
    private static TypeFactory TYPE_FACTORY = mapper.getTypeFactory();
    static{
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);//设置序列化配置，为null的属性不加入到json中
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);//兼容单引号 但单引号不属于json标准 不建议使用
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String write2JsonStr(Object o) throws JsonProcessingException {
            return mapper.writeValueAsString(o);
    }
    public static <T> T json2Object(String json, Class<T> clz) throws JsonProcessingException {
        return mapper.readValue(json,clz);
    }
    public static <T> List<T> json2ObjectList(String json, Class<T> clazz) throws JsonProcessingException {
        JavaType t  = TYPE_FACTORY.constructParametricType(List.class, clazz);
        return mapper.readValue(json, t);
    }
    public static Set<?> json2ArraySet(String json, TypeReference<?> tr) throws JsonProcessingException {
            return (Set<?>) mapper.readValue(json, tr);

    }
}

