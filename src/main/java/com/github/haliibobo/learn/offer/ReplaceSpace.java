package com.github.haliibobo.learn.offer;

import org.junit.Test;

public class ReplaceSpace {

    /*
     *请实现一个函数，将一个字符串中的每个空格替换成“%20”。
     *例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     */
    @Test
    public  void solution() {
        StringBuffer stringBuffer = new StringBuffer();
        System.out.println(stringBuffer.length());
        stringBuffer.append("We are happy");
        System.out.println(replaceSpace(stringBuffer));
    }

    /*
    问题1：替换字符串，是在原来的字符串上做替换，还是新开辟一个字符串做替换！
    问题2：在当前字符串替换，怎么替换才更有效率（不考虑java里现有的replace方法）。
          从前往后替换，后面的字符要不断往后移动，要多次移动，所以效率低下
          从后往前，先计算需要多少空间，然后从后往前移动，则每个字符只为移动一次，这样效率更高一点。
    */
    private  String replaceSpace(StringBuffer str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return "";
        }
        int spaceCount = str.toString().split(" ",-1).length -1;
        if (spaceCount < 0) {
            return str.toString();
        }
        //计算插入以后的字符串长度
        int oldLength = str.length();
        int newLength = str.length() + spaceCount * 2;
        //重置长度
        str.setLength(newLength);
        int cur = newLength -1;

        //依次拷贝,从后往前
        for (int i = oldLength - 1; i >= 0; i--) {
            if (str.charAt(i) != ' ') {
                str.setCharAt(cur--, str.charAt(i));
            } else {
                str.setCharAt(cur--, '0');
                str.setCharAt(cur--, '2');
                str.setCharAt(cur--, '%');
            }
        }
        return str.toString();
    }

    private  String replaceSpace2(StringBuffer str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return "";
        }
        return str.toString().replace(" ","%20");
    }
    private  String replaceSpace3(StringBuffer str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return "";
        }
        StringBuffer s = new StringBuffer();
        for (char c : str.toString().toCharArray()) {
            if (c == ' '){
                s.append("%20");
            }else {
                s.append(c);
            }
        }
        return s.toString();
    }
}
