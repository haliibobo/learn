package com.github.haliibobo.learn.java.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-05-15 12:17
 * @description describe what this class do
 */
public class TestBean {

    private Map<FeedBackType, List<Fb>> feedbackMap;
    private StringBuffer s;

    public TestBean(){
        this.feedbackMap = new HashMap<>();
        this.feedbackMap.put(FeedBackType.s3,null);
        this.s= new StringBuffer(" java");
    }

    public Map<FeedBackType, List<Fb>> getFeedbackMap() {
        return feedbackMap;
    }

    public void setFeedbackMap(
        Map<FeedBackType, List<Fb>> feedbackMap) {
        this.feedbackMap = feedbackMap;
    }

    public StringBuffer getS() {
        return s;
    }

    public void setS(StringBuffer s) {
        this.s = s;
    }

    public static void main(String[] args) {
        TestBean testBean = new TestBean();

        StringBuffer ss = testBean.getS();
        StringBuffer sss = new StringBuffer();
        ss =sss;
        sss.append(" World");
        System.out.println(sss);
        System.out.println(testBean.getS());



        Map<FeedBackType, List<Fb>> map =testBean.getFeedbackMap();
        if(map==null||map.isEmpty()){
            map= new HashMap<>();//就是另开辟一块内存地址，而之前的地址若依旧有引用，则不会指向新地址
            map.put(FeedBackType.s1,null);
        }else{
            map.put(FeedBackType.s2,null);
        }
        System.out.println(testBean.getFeedbackMap());
    }


    public  enum FeedBackType {
        s1, s2,s3

    }

    public static class Fb {}
}
