package com.github.haliibobo.learn.offer;

public class ReplaceSpace {

    /*
     *请实现一个函数，将一个字符串中的每个空格替换成“%20”。
     *例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     */
    public static void main(String[] args) {
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
    private static String replaceSpace(StringBuffer stringBuffer) {
        if (stringBuffer == null) {
            return null;
        }
        if (stringBuffer.length() == 0) {
            return "";
        }
        //TODO 做错的地方 分割字符串获取分割以后字符数组的长度 空格在字符串末尾不通过
        /*int spaceCount = stringBuffer.toString().split(" ").length;
        if (spaceCount <= 1) {
            return stringBuffer.toString();
        }*/
        int spaceCount = 0;
        for(int i = 0; i< stringBuffer.length();i ++){
            if(stringBuffer.charAt(i) == ' '){
                spaceCount++;
            }
        }
        //计算插入以后的字符串长度
        int oldLength = stringBuffer.length();
        int newLength = stringBuffer.length() + spaceCount * 2;
        //重置长度
        stringBuffer.setLength(newLength);
        //TODO 做错的地方 直接使用newLength取字符 导致数组越界
        newLength--;

        //依次拷贝
        for (int i = oldLength - 1; i >= 0; i--) {
            if (stringBuffer.charAt(i) != ' ') {
                //TODO 做错的地方，使用oldLength 应该使用指针i
                stringBuffer.setCharAt(newLength--, stringBuffer.charAt(i));
            } else {
                stringBuffer.setCharAt(newLength--, '0');
                stringBuffer.setCharAt(newLength--, '2');
                stringBuffer.setCharAt(newLength--, '%');
            }
        }
        return stringBuffer.toString();
    }
}
