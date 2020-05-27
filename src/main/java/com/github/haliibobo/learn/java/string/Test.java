package com.github.haliibobo.learn.java.string;


//评测题目: 无

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/***
 2选一即可
 问题一：
 尝试编写一个生产消费模型
 1个生产线程 2个消费线程
 生产者发送任务 可以是顺序发送数字 1-20
 消费者消费任务 把消费者线程ID以及任务的序号打印出来
 生产线程在发送20个任务后, 结束
 (确保任务均被消费线程执行，确保最后所有线程已全部退出)

 问题二：
 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 示例：
 输入: S = "ADOBECODEBANC", T = "ABC"
 输出: "BANC"
 说明：找不到返回""，找得到返回一个即可
 */
public class Test {


    @org.junit.Test
    public void test2(){
        System.out.println(1<<31);
        System.out.println(Integer.MIN_VALUE);
        System.out.println((1<<31) -1);
        System.out.println((int) (Math.pow(2,32)-1));
        System.out.println(Integer.MAX_VALUE);
        int[] a = {22,18,178,33,1 << 0};
        System.out.println("[" +new String(a,0,a.length) +"]");
        System.out.println((1 << 31) -1);
        System.out.println((Math.pow(2,31)-1));
        System.out.println((int) (Math.pow(2,32)-1));

        System.out.println((1 << 2) - 1);
        System.out.println((int) (Math.pow(2,2)-1));
    }


    @org.junit.Test
    public void test (){
        System.out.print(find2("ADOBECODEBANC","ABC"));
    }

    public String find (String s,String t){
        if(s == null || t == null){
            return "";
        }
        boolean flag= false;
        int left = 0;
        int lengthOfMin = s.length();
        int minLeft=0;
        int minRight=0;
        int cnt=0;
        Map<Character,Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int right = 0; right <s.length() ; right++) {
            char c = s.charAt(right);
            if (map.containsKey(c)){
                cnt =map.get(c) > 0 ? cnt+1 : cnt;
                map.put(c,map.get(c)-1);
            }
            while (cnt==map.size()){
                flag=true;
                if(right-left+1<=lengthOfMin){
                    lengthOfMin = right-left+1;
                    minLeft = left;
                    minRight = right;
                }
                char cs = s.charAt(left);
                if (map.containsKey(cs)){
                    if (map.get(cs)+1>0) cnt--;
                    map.put(cs,map.get(cs)+1);
                }
                left++;
            }
        }
        if (!flag)return "";
        return s.substring(minLeft,minRight+1);
    }

    public String find2 (String s,String t){
        if(s == null || t == null){
            return "";
        }
        boolean flag= false;
        int left = 0;
        int lengthOfMin = s.length();
        int minLeft=0;
        int minRight=0;
        int cnt=0;
        Set<Character> set = new HashSet<>();
        for (char c : t.toCharArray()) {
            set.add(c);
        }
        for (int right = 0; right <s.length() ; right++) {
            char c = s.charAt(right);
            if (set.contains(c)){
                cnt++;
            }
            while (cnt==set.size()){
                flag=true;
                if(right-left+1<=lengthOfMin){
                    lengthOfMin = right-left+1;
                    minLeft = left;
                    minRight = right;
                }
                char cs = s.charAt(left);
                if (set.contains(cs)){
                    cnt--;
                }
                left++;
            }
        }
        if (!flag)return "";
        return s.substring(minLeft,minRight+1);
    }
}
