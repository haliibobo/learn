package com.github.haliibobo.learn.java.collection;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrator
 *
 */
public class GsonUtil {

 /**
  * 实现格式化的时间字符串转时间对象
  */
 private static final String DATEFORMAT_DEFAYLT = "yyyy-MM-dd HH:mm:ss";
 private GsonUtil() {
     throw new IllegalAccessError("GsonUtil class");
 }

 /**
  * 使用默认的gson对象进行反序列化
  * @param json
  * @param typeToken
  * @return
  */
 public static <T> T fromJsonDefault(String json, TypeToken<T> typeToken) {
  Gson gson = new Gson();
  return gson.fromJson(json, typeToken.getType());
 }
 /**
  * json字符串转list或者map
  * @param json
  * @param typeToken
  * @return
  */
 public static <T> T fromJson(String json, TypeToken<T> typeToken) {
  Gson gson = new GsonBuilder()
  /**
   * 重写map的反序列化
   */
  .registerTypeAdapter(new TypeToken<Map<String, Object>>() {
  }.getType(), new MapTypeAdapter()).create();
  return gson.fromJson(json, typeToken.getType());
 }

 /**
  * json字符串转bean对象
  * @param json
  * @param cls
  * @return
  */
 public static <T> T fromJson(String json, Class<T> cls) {
  Gson gson = new GsonBuilder().setDateFormat(DATEFORMAT_DEFAYLT)
    .create();
  return gson.fromJson(json, cls);
 }

 /**
  * 对象转json
  * @param obj
  * @param format
  * @return
  */
 public static String toJson(Object obj, boolean format) {

  GsonBuilder gsonBuilder = new GsonBuilder();
  /**
   * 设置默认时间格式
   */
  gsonBuilder.setDateFormat(DATEFORMAT_DEFAYLT);

  /**
   * 添加格式化设置
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
  private Object numberType(JsonReader in ) throws IOException{
   /**
    * 改写数字的处理逻辑，将数字值分为整型与浮点型。
    */
   double dbNum = in.nextDouble();

   // 数字超过long的最大值，返回浮点类型
   if (dbNum > Long.MAX_VALUE) {
    return dbNum;
   }

   // 判断数字是否为整数值
   long lngNum = (long) dbNum;
   if(dbNum > lngNum){
    return dbNum;
   }else if(dbNum < lngNum){
    return dbNum;
   } else {
    return lngNum;
   }
  }
  private Object mapType (JsonReader in ) throws IOException{
   Map<String, Object> map = new LinkedTreeMap<String, Object>();
   in.beginObject();
   while (in.hasNext()) {
    map.put(in.nextName(), read(in));
   }
   in.endObject();
   return map;
  }
  private Object arrayType (JsonReader in ) throws IOException{
   List<Object> list = Lists.newArrayList();
   in.beginArray();
   while (in.hasNext()) {
    list.add(read(in));
   }
   in.endArray();
   return list;
  }

 }

 /**
  * 集合深拷贝.
  */
 @SuppressWarnings("unchecked")
 public static <T> List<T> deepCopy(List<T> src) {

  List<T> dest = new ArrayList<T>(src.size());
  try {
   ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
   ObjectOutputStream out = new ObjectOutputStream(byteOut);
   out.writeObject(src);

   ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
   ObjectInputStream in = new ObjectInputStream(byteIn);
   dest = (List<T>) in.readObject();

  } catch (Exception e) {
  }
  return dest;
 }

 public  static void main(String[] args){
  String si_prsdata_redis ="[{room:\"prsDataLf\",configId:\"jim://2672240584298048807/3408\",token:\"jim://2672240584298048807/3408\"}," +
    "{room:\"prsDataMjq\",configId:\"jim://2674911953408580583/3357\",token:\"jim://2674911953408580583/3357\"}," +
    "{room:\"prsDataHt\",configId:\"jim://2570476378403829031/5037\",token:\"jim://2570476378403829031/5037\"}]";

  List<Map<String,String>> list=GsonUtil.fromJson(si_prsdata_redis,new TypeToken<List<Map<String,String>>>(){});
  System.out.println(list);
  //leiyipin
  String map ="{\"mj3\":[{\"st\":1550163600,\"et\":1551369599,\"pid\":50000123768,\"p_cd\":4,\"m\":300.2,\"n\":30.0}]}";

  Map<String, String> dataMapOld = new HashMap<String, String>();
  Map<String, String> dataMap = new HashMap<String, String>();
  Map<String,Object> maps = GsonUtil.fromJson(map, new TypeToken<Map<String, Object>>() {
  });
  for (Object obj : maps.keySet()){
   dataMap.put(obj.toString(), GsonUtil.toJson(maps.get(obj),false));
   dataMapOld.put(obj.toString(), maps.get(obj).toString());
  }
  System.out.println("原始json:");
  System.out.println(map);
  System.out.println("旧map:");
  System.out.println(dataMapOld);
  System.out.println("旧map 调用json:");
  System.out.println(GsonUtil.toJson(dataMapOld,false));
  System.out.println("新map:");
  System.out.println(dataMap);
  System.out.println("旧map 调用json:");
  System.out.println(GsonUtil.toJson(dataMap,false));

  //String

 }

}