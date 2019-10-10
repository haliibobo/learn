package com.github.haliibobo.learn.java;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.protobuf.Message;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;
import javafx.scene.effect.SepiaTone;

/**
 * @Auther: lizibo
 * @Date: 2018/10/23 15:26
 * @Description:
 */
public class Test {

    private static String parseArrayToJson(String text) {
        StringBuilder json = new StringBuilder('[');
        if (text != null && text.length() > 0) {
            String[] ss = text.split(String.valueOf((char) 0x02),-1);
            for (String s : ss) {
                json.append(s).append(",");
            }
        }
        if (',' == (json.charAt(json.length()-1))) {
            return json.substring(0,json.length()-1) + ']';
        }else {
            json.append(']');
            return json.toString();
        }

    }

    private boolean isNull(String s){
        return Strings.isNullOrEmpty(s)||"null".equalsIgnoreCase(s);
    }


    public static void main(String[] args){

        /*System.out.println();

        Map map1 =new HashMap<String,String>();
        Map map2 =new HashMap<String,String>();
        map1.put("1","1");
        map1.put("2","2");

        map2.put("1","1");
        map2.put("2","2");

        List list1 = new ArrayList();
        List list2 = new ArrayList();

        list1.add(map1);
        list2.add(map2);
        list2.removeAll(list1);
        System.out.println(list2);

        String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(new java.util.Date(1545228000000L));
        System.out.println(date);*/

       /* Scanner scanner = new Scanner(System.in);
        int n =scanner.nextInt();
        int b=scanner.nextInt();
        System.out.println(caculate(n,b));*/
       /*System.out.println( Arrays.asList(("").split(",")).stream().map(s->Integer.parseInt(s)).collect(
           Collectors.toList()));*/
       /*System.out.println(Integer.parseInt("001001"));
       long  a = 481284317473010L;
       String massage ="";
       System.out.println(massage);
       System.out.println(initDateByDay());
        System.out.println(initDateByDay2());*/

       // System.out.println(Integer.parseInt("1000082267"));
        /*System.out.println(new StringBuilder('[').capacity());
        System.out.println('[');
        System.out.println( (char) 91);
        System.out.println('[' - 91);*/
        String s= "{\"mct\":\"iPhone\",\"proj_id\":\"1\",\"chf\":\"apple_store\",\"token\":\"e8141d57ef0ffc9e8195eb0d8ede3a93\",\"net\":\"wifi\",\"scr\":\"828*1792\",\"uid\":\"6f7173c11e908520b17b6e285f088364f2959e52\",\"idfa\":\"73C1441F-FA58-48EB-9A8E-A5D98891BD02\",\"ver\":\"5.3.0\",\"jid\":\"6f7173c11e908520b17b6e285f088364f2959e52\",\"uip\":\"111.202.148.44\",\"std\":\"JA2015_311210\",\"jvr\":\"5.4.7\",\"user_agent\":\"%E4%BA%AC%E4%B8%9C\\/8.0.0 CFNetwork\\/978.0.7 Darwin\\/18.7.0\",\"rtm\":1569811508721,\"osv\":\"12.4\",\"app_device\":\"IOS\",\"report_ts\":\"1569811508702\",\"osp\":\"iphone\",\"dvc\":\"iPhone11,8\",\"clt\":\"app\",\"machineType\":\"iPhone\",\"vts\":\"2192\",\"rpr\":\"{\\\"page_id\\\":\\\"Auction_NewProduct\\\",\\\"page_param\\\":\\\"57212894677_5_111307038\\\",\\\"shopId\\\":\\\"\\\",\\\"shop_id\\\":\\\"\\\"}\",\"ctp\":\"Auction_NewProduct_page\",\"seq\":\"9\",\"imsi\":\"46002\",\"lon\":\"116.556426\",\"jda_ts\":\"1568594520\",\"apv\":\"8.0.0\",\"fst\":\"1533867218447\",\"ref_cls\":\"PagePushContrl_pop_allpage\",\"m_source\":\"1\",\"ctm\":\"1569811508700\",\"jdv\":\"0|kong|t_1000170135|tuiguang|notset|1568874464855|1568874464\",\"ref\":\"Auction_NewProduct_page\",\"bld\":\"166245\",\"ext\":\"{\\\"page_id\\\":\\\"Auction_NewProduct\\\",\\\"page_param\\\":\\\"57285904137_5_111317793\\\",\\\"shopId\\\":\\\"\\\",\\\"shop_id\\\":\\\"\\\"}\",\"par\":\"{\\\"page_id\\\":\\\"Auction_NewProduct\\\",\\\"page_param\\\":\\\"57285904137_5_111317793\\\",\\\"shopId\\\":\\\"\\\",\\\"shop_id\\\":\\\"\\\"}\",\"rpd\":\"Auction_NewProduct\",\"unionwsws\":\"{\\\"jmafinger\\\":\\\"bDaq2C7EUFJG0nZj\\\\\\/F9HmLmBcCUg2zMkPOa7Xq1Agn2v4Rmsh1P0YyFukloclx3KlMC3KiqoAZ\\\\\\/+KIf5dvmOWrA==\\\",\\\"devicefinger\\\":\\\"eidI210B0111NUJFNTc2NzItQTdDMy00NA==bGkg1LH\\\\\\/ODWVEgFayC1qB8pKoLpZ78GdVLWIrRL0dEr1XEVhckXKw73YGwvyGmcmtBvTB8BqH9xu6MUt\\\"}\",\"umd\":\"tuiguang\",\"pin\":\"TXTcPkczEhUW0N92W6sPLg==\",\"open_flag\":\"0\",\"vct\":\"1569811271534\",\"ucp\":\"t_1000170135\",\"pv_seq\":\"13\",\"page_id\":\"Auction_NewProduct\",\"usc\":\"kong\",\"ref_clp\":\"{\\\"page_param\\\":\\\"\\\",\\\"event_param\\\":\\\"9_JDSHQRContainerViewController\\\"}\",\"ims\":\"中国移动\",\"unpl\":\"ADC_8bnLO7yZsbahzGg2i7stfZDJuErrzc8Kli2NeJMjJ2XizEwj+4Y7iQdaOArulsG+g3rPJCDIvWhZ0dUdW5T3lCmaQ7lcp24mAofl1PIL6+7XVrG7xwlkHKSuzKsKwYaJRhg+r2V4Ihzq\\/4X9\\/8VL5xLk2khCxSi5BRHhCycVhyg=|ADC_PyheCM9zeE9D5\\/W+AFkEGe5HbGtnOm3zaF5Zdefv2NhpFjf6JpMNk+4IAkYIyDyjofXP8sqCs9c\\/dq5OLB1Z2wYwpJrOM7dEtatxy+zd6iQohUfKTVNT+3kmRC43JkuobH1GBq5VLnS3nXYYxWZDv41zxxNSYgMrl84RbbOoa3Fic\\/7\\/gCxQUo6pWfJye6bKNv+oJgcKneu\\/utoqjvUWjW5BLvTxSvrTt+L7mvwvJQNAup8PoSx5IXspac9NaCHnb5N7A+ehlegDPmlWhtkQh+X+Ud2J70\\/H7ZxYJrrVanp7+FMZeaeVRivaxFcANz62AhhOEwJN3Um+Q25D2FN83Wsd4HC6W3hQH1ecY1Le2iy3RtOrXS7pQ\\/eaVrw\\/w6e\\/SFbfefLqCk9oGqoANDN2bZjDJdtx1OCL1Fff4v8wgxR+tsZVxzKQDb8luZR\\/4\\/etxsibJa6eqBw2PeNeo84aZHS7nfXHB7N0D3PnD2zaTJOrj8msFCIKRfBRXF+8U1FWLC15zVtYnPgFBDQwqFMD5VMep3q0m+mcoQ3aameDwperA8SkjcxN5EsVc6rorMcti6yELyN7dR6IdM4vTzrkgWZDij0bK3\\/aDbFDlH92P9tA69Ynb1X2W8oCq7MRIGsw\",\"utr\":\"notset\",\"nty\":\"wifi\",\"lat\":\"39.786308\",\"pv_sid\":\"716\",\"pst\":\"1569810945441\",\"psn\":\"1552723073334316204569|424\",\"pap\":\"1552723073334316204569|424\",\"psq\":\"8\",\"typ\":\"pv\"}";
        JSONObject json = JSONObject.parseObject(s);
        String skuinfo = json.getJSONObject("par").getString("page_param");
        String  sku = skuinfo.split("_",-1)[2];
        System.out.println(sku);
    }
    /**
     * 获得当天零时零分零秒
     * @return
     */
    public static long initDateByDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR, 00);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime().getTime();
    }
    public static long initDateByDay2() {
        long nowTime = System.currentTimeMillis();
        return
            nowTime - (nowTime + TimeZone.getDefault().getRawOffset()) % (1000 * 3600 * 24);
    }
    public  static  int caculate(int n,int b){
        int count =0;
        for (int i = b; i <=n ; i++) {
            if(n%b==0){
                count++;
            }
        }
        Set set =new CopyOnWriteArraySet();
        return count;
    }

 /*   private Map<String,Object> map ( Map<String, Message> map){

         Map<String, ByteBuffer> byteBufferMap = new HashMap<>();

        Map<String, Scanner> collect = map.entrySet().stream()
            .collect(Collectors.toMap(
                stringListEntry -> stringListEntry.getKey(),
                stringListEntry -> new Scanner(stringListEntry.getValue())));


         Map<String,Object> m = new HashMap<>(map);
         return m;

    }*/
}
