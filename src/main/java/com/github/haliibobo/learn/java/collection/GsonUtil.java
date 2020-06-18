package com.github.haliibobo.learn.java.collection;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

/**
 * gson工具类.
 * @author Administrator
 */
public class GsonUtil {

    /**
     * 实现格式化的时间字符串转时间对象.
     */
    private static final String DATEFORMAT_DEFAYLT = "yyyy-MM-dd HH:mm:ss";

    private  static final InstanceCreator byteBufferInstanceCreator = type -> ByteBuffer.allocate(0);
    private  static final GsonBuilder gsonBuilder = new GsonBuilder();

    static {
        gsonBuilder.registerTypeAdapter(ByteBuffer.class,byteBufferInstanceCreator);
    }

    private GsonUtil() {
        throw new IllegalAccessError("GsonUtil class");
    }

    /**
     * 使用默认的gson对象进行反序列化.
     */
    public static <T> T fromJsonDefault(String json, TypeToken<T> typeToken) {
        return gsonBuilder.create().fromJson(json, typeToken.getType());
    }

    /**
     * json字符串转list或者map.
     */
    public static <T> T fromJson(String json, TypeToken<T> typeToken) {
        Gson gson = gsonBuilder
            /**
             * 重写map的反序列化.
             */
            .registerTypeAdapter(new TypeToken<Map<String, Object>>() {
            }.getType(), new MapTypeAdapter()).create();
        return gson.fromJson(json, typeToken.getType());
    }

    /**
     * json字符串转bean对象.
     */
    public static <T> T fromJson(String json, Class<T> cls) {
        Gson gson = gsonBuilder.setDateFormat(DATEFORMAT_DEFAYLT)
            .create();
        return gson.fromJson(json, cls);
    }

    /**
     * 对象转json.
     */
    public static String toJson(Object obj, boolean format) {
        /**
         * 设置默认时间格式.
         */
        gsonBuilder.setDateFormat(DATEFORMAT_DEFAYLT);

        /**
         * 添加格式化设置.
         */
        if (format) {
            gsonBuilder.setPrettyPrinting();
        }
        Gson gson = gsonBuilder.create();
        return gson.toJson(obj);
    }

    public static class MapTypeAdapter extends TypeAdapter<Object> {

        @Override
        public Object read(JsonReader in) throws IOException {
            JsonToken token = in.peek();
            switch (token) {
                case BEGIN_ARRAY:
                    return arrayType(in);

                case BEGIN_OBJECT:
                    return mapType(in);

                case STRING:
                    return in.nextString();

                case NUMBER:
                    return numberType(in);

                case BOOLEAN:
                    return in.nextBoolean();

                case NULL:
                    in.nextNull();
                    return null;

                default:
                    throw new IllegalStateException();
            }
        }

        @Override
        public void write(JsonWriter out, Object value) throws IOException {
            // 序列化无需实现
        }

        private Object numberType(JsonReader in) throws IOException {
            /**
             * 改写数字的处理逻辑，将数字值分为整型与浮点型.
             */
            double dbNum = in.nextDouble();

            // 数字超过long的最大值，返回浮点类型
            if (dbNum > Long.MAX_VALUE) {
                return dbNum;
            }

            // 判断数字是否为整数值
            long lngNum = (long) dbNum;
            if (dbNum > lngNum) {
                return dbNum;
            } else if (dbNum < lngNum) {
                return dbNum;
            } else {
                return lngNum;
            }
        }

        private Object mapType(JsonReader in) throws IOException {
            Map<String, Object> map = new LinkedTreeMap<String, Object>();
            in.beginObject();
            while (in.hasNext()) {
                map.put(in.nextName(), read(in));
            }
            in.endObject();
            return map;
        }

        private Object arrayType(JsonReader in) throws IOException {
            List<Object> list = Lists.newArrayList();
            in.beginArray();
            while (in.hasNext()) {
                list.add(read(in));
            }
            in.endArray();
            return list;
        }

    }
}