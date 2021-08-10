package com.github.haliibobo.learn;

public class Test10 {

    public static void main(String[] args) {
         String s = " this is tencent, tencenta not a correct,tencent is ok ";
         String target = "tencent";
         System.out.println(count(s,target));
    }

    public  static int count( String s,String target ) {
        int count = 0;
        StringBuilder tmp = new StringBuilder();
        for (char c :s.toLowerCase().toCharArray()){
            if (c >= 97 &&  c <= 122 ){
                tmp.append(c);
            }else {
                if (target.equals(tmp.toString())) {
                    count++;
                }
                tmp = new StringBuilder();
                }
            }
       /* for (String ss : tmp.toString().split("#")){
            if (target.equals(ss)) {
                count++;
            }
        }*/
        return count;
    }

}
