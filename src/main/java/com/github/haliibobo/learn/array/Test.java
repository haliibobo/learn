package com.github.haliibobo.learn.array;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * 虽然类和数组都是对象，但是java虚拟机对类实例和数组的创建和操作使用了不同的字节码指令
 * 创建类实例指令：new
 * 创建数组指令 ：newarray、anewarray、multianewarray
 * 访问类字段（static）getstatic、putstatic
 * 访问实例字段 getfield、putstatic
 * 方法调用
 * invokevirtual 调用实例方法
 * invokeinterface 调用接口方法
 * invokespecial 调用特殊处理方法，例如 实例初始化、私有、父类方法
 * invokestatic 调用类方法
 * invokedynamic 运行动态解析方法
 *
 * 类加载过程
 * 数组类本身不通过类加载器创建，而是由java虚拟机直接创建
 */
public class Test {
    private int i;
    public short s;

    @Override
    public int hashCode() {
        return Objects.hashCode(i);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;

        }else if (obj instanceof Test){
            Test o = (Test) obj;
            return o.i==this.i;
        }
        return false;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {

        "s".getBytes();
        Test t = new Test();
        t.setI(0);
        int a =0;
        String[] ss = new String[2];

        byte[][] by= new byte[6][7];
        char[] c = new char[1];
        short[] s = new short[1];
        int[][] i = new int[7][];
        long[] l = new long[7];
        double[] d =  new double[1];
        float[] f= new float[1];
        boolean[][] b = new boolean[6][7];

        Integer[][] I = new Integer[6][7];

        Test[] tt = new Test[2];
        tt[0] =t;
        Test[] ttt = tt;
        Test[] tttt = new Test[2];
        tttt[0] =t;
        System.out.println(t.getClass().getName());
        System.out.println(t.getClass().getConstructors().length);
        System.out.println(tt);
        System.out.println(ttt);
        System.out.println(tttt);
        System.out.println(tt.getClass().getName());
        System.out.println(tt.getClass().getConstructors().length);
        System.out.println(i.getClass().getName());//引导类加载器
        System.out.println(i.getClass().getConstructors().length);
        System.out.println(I.getClass().getName());//类加载器
        System.out.println(b.getClass().getName());
        System.out.println(by.getClass().getName());
        System.out.println(s.getClass().getName());
        System.out.println(ss.getClass().getSuperclass().getName());

        Object obj = c ; //数组的父类也是Object,可以将a向上转型到Object
        Object[] objs = I;
        //2		那么能向下转型吗?
        char[] chars = (char[]) obj;  //可以进行向下转型

        //3		能使用instanceof关键字判定吗?
        if(obj instanceof char[]){  //可以用instanceof关键字进行类型判定
            System.out.println("obj的真实类型是char[]");
        }

        //虚拟机自动创建了数组类型，数组类型和8种基本数据类型一样， 当做java的内建类型
        //每一维度用一个[表示；开头两个[，就代表是二维数组
        //如果是内建8种类型 就是基本类型
        //但是无法使用反射 拿出这个length 属性
        //java.lang.reflect.Field fieldarr = I.getClass().getField("length");
        //获取本类 直接声明的属性
        Field fieldobj2= t.getClass().getDeclaredField("s");
        //获取继承链上的public 属性
        Field fieldobj = t.getClass().getField("s");

    }
}
