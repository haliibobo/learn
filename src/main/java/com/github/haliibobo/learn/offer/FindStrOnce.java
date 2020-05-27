package com.github.haliibobo.learn.offer;

import java.util.LinkedHashMap;

public class FindStrOnce {

    public static void main(String[] args) {
        FindStrOnce findStrOnce = new FindStrOnce();
        String s = "BabyBaby";
        for (char c : s.toCharArray()) {
            findStrOnce.Insert(c);
            System.out.print(findStrOnce.FirstAppearingOnce());
        }
    }


    LinkedHashMap<Character,Integer> m = new LinkedHashMap<>();
    //Insert one char from stringstream
    public void Insert(char ch)
    {
        m.put(ch,m.get(ch) == null?1:m.get(ch)+1);

    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        for (Character character : m.keySet()) {
            if (m.get(character)==1){
                return character;
            }
        }
        return '#';
    }
}
