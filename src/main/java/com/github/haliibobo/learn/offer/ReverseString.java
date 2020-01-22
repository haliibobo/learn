package com.github.haliibobo.learn.offer;

public class ReverseString {

    public static void main(String[] args) {

        String s = "a%$^$%b,ssc";
        new StringBuilder().reverse();
        char[] c =s.toCharArray();
        reverse(c);
        System.out.print(new String(c));

    }

    public static void reverse(char str[]){
        int l =0;
        int r = str.length -1 ;
        while (l < r) {
            if (!Character.isAlphabetic(str[l])) {
                l++;
            } else if (!Character.isAlphabetic(str[r])){
                r--;
            }else {
                char tmp =str[r];
                str[r] = str[l];
                str[l] =tmp;
                l++;
                r--;
            }
        }

}
}
