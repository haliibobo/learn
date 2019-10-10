package com.github.haliibobo.learn.java.inte.consumer;

import java.util.function.Consumer;

/**
 * @Auther: lizibo
 * @Date: 2018/10/25 21:47
 * @Description:Consumer 接口翻译过来就是消费者,顾名思义，该接口对应的方法类型为接收一个参数，没有返回值，可以通俗的理解成将这个参数'消费掉了'，一般来说使用Consumer接口往往伴随着一些期望状态的改变或者事件的发生,例如最典型的forEach就是使用的Consumer接口，虽然没有任何的返回值，但是却向控制台输出了语句。
     Consumer 使用accept对参数执行行为
 */
public class Test {
    public static void main(String[] args) {
        String clp ="1542335195#10156463812#beac1d7bdbe249be624aa3dbd89747cd15d62ab7#7c18e7cc-5940-42f7-9aa7-fe050940869f#20#1#0#119#-100#-100#1#0#-100#0#-100#-100#1#-100#1#0#-100";

        String sku = clp.split("-")[0];
        boolean isNum = sku.matches("[0-9]+");
        if(!isNum){
            sku =clp.split("#")[1];
        }

        System.out.println(sku);
    }

}
