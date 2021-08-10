package com.github.haliibobo.learn.offer;

public class ReverseString2 {

    public static void main(String[] args) {

        String s = " this ok".trim();
        char[] c =s.toCharArray();
        reverse(c);
        System.out.print(new String(c));

    }

    public static void reverse(char[] str){
        // 每 k 个一组进行反转
        int n = str.length;
        int pre = 0;
        for (int i = 0; i < n/2; i++) {
            char tmp = str[i];
            str[i] = str[n-1-i];
            str[n-1-i] = tmp;
        }
        for (int next = 0; next < n; next++) {
            if (str[next] == 32){
                for (int j = pre; j < pre +(next-pre)/2; j++) {
                    char tmp = str[j];
                    str[j] = str[next-1-j+pre];
                    str[next-1-j+pre] = tmp;
                }
                pre = next+1;
            }
            if (next == n-1){
                for (int j = pre; j <= pre +(next-pre)/2; j++) {
                    char tmp = str[j];
                    str[j] = str[next-j+pre];
                    str[next-j+pre] = tmp;
                }
            }
        }
    }
}
