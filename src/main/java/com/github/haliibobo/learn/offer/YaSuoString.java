package com.github.haliibobo.learn.offer;

public class YaSuoString {
    public static void main(String[] args) {
        String s = "aaaaaaaaaaaabzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
        char[] chars = s.toCharArray();
        int lng = 1;
        int left= 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i-1] ==  chars[i]){
                lng ++;
            }
            if (chars[i-1] !=  chars[i] ||i == chars.length-1 ){
                if (lng > 1){
                    char[] tmp=  Integer.toString(lng).toCharArray();
                    for (char c : tmp) {
                        chars[left++] = c;
                    }
                }
                if (chars[i-1] !=  chars[i]){
                    chars[left++] = chars[i];
                }
                lng=1;
            }

        }
        System.out.println(left);
        System.out.println(new String(chars));
    }
}
