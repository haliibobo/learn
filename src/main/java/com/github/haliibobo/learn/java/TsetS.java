package com.github.haliibobo.learn.java;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import com.github.haliibobo.learn.java.tree.TreeNode;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author lizibo
 * @version 1.0
 * @date 2019-02-18 18:32
 * @description TODO
 */
public class TsetS {


    @Test

    public void lengthOfLongestSubstring() {
        String s = "abcabc";
        if (s.length() == 0) {
            System.out.println(0);
            return;
        }
        Set<Character> set = new HashSet<>();
        //滑动窗口
        int i = 0;//左指针
        int j = 1;//右指针
        int max = 1;
        while (i < s.length() && j < s.length()) {
            //当前的最大子串 包含最新的字符
            if (set.contains(s.charAt(j))) {
                i += s.substring(i, j).indexOf(s.charAt(j)) + 1;
                set.remove(s.charAt(j));
            } else {
                set.add(s.charAt(j));
            }
            j++;
            max = Math.max(max, j - i);

        }
        System.out.println(max);
    }

    public static void main(String[] args) {
        String path = "/user/recsys/recpro/unifiedfeed/iteminfo/detaildiff/201910200000";
        String offTime = path.substring(path.lastIndexOf("/") + 1);
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        int tmp = 0;
        for (int i = 0; i < 3; i++) {
            System.out.println(tmp++);
            ;
        }


        try {
            if (System.currentTimeMillis() > sdf.parse(offTime).getTime()) {
                System.out.println("hao");
                System.out.println(new TsetS().getClass().getSimpleName());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(StringUtils.isNumeric(null));

    }

    @Test
    public void test2() {
        int sum = 22;
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode8 = new TreeNode(8);
        TreeNode treeNode11 = new TreeNode(11);
        TreeNode treeNode13 = new TreeNode(13);
        TreeNode treeNode4_ = new TreeNode(4);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode5_ = new TreeNode(5);
        TreeNode treeNode1 = new TreeNode(1);

        treeNode5.left = treeNode4;
        treeNode5.right = treeNode8;
        treeNode4.left = treeNode11;
        treeNode11.left = treeNode7;
        treeNode11.right = treeNode2;
        treeNode8.left = treeNode13;
        treeNode8.right = treeNode4_;
        treeNode4_.left = treeNode5_;
        treeNode4_.right = treeNode1;


        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        cal(treeNode5, res, tmp, sum);
        System.out.println(res);
    }


    @Test
    public void  testtt(){
        reverse(123);
    }

    public int reverse(int x) {

        boolean neg =x < 0;
        long tmp = neg?-x:x;
        long now=0;
        Deque<Long> q = new ArrayDeque<>();
        while( tmp > 9){
            q.add(tmp%10);
            tmp = tmp/10;
            System.out.println(tmp);
        }
        q.add(tmp);
        while(q.peek()!=null){
            now = now*10 +q.pop();
        }

        if (neg)
            now = -now;
        if (now >Integer.MAX_VALUE || now < Integer.MIN_VALUE){
            return 0;
        }
        return (int) now;
    }


    public void cal(TreeNode root, List<List<Integer>> res, List<Integer> tmp, int sum) {
        if (root == null) {
            return;
        }
        sum = sum - root.val;
        tmp.add(root.val);
        if (root.left == null && root.right == null) {
            if (sum == 0) {
                res.add(new ArrayList<>(tmp));
                //回溯到上一步
               tmp.remove(tmp.size() - 1);
               return;
            }
        }
        if (root.left != null) {
            cal(root.left, res, tmp, sum);
        }
        if (root.right != null) {
            cal(root.right, res, tmp, sum);
        }
        tmp.remove(tmp.size() - 1);
    }


    @Test
    public void test4444(){
        String[] dictionary ={"bt","vbtbvtvvbbvtbvvbbbvbtbbv","bvvbbbvvvbvttbtbvtvtvvbttbbbtvvvb","btttbvbbbtbbtbvvttbvbvtvbtbbttb","bt","vvbvbvbvtbvbvvvvtv","tbvvvtttvtbvbtttvvbtvvvvtvvbvvtvvbbvbbbvb","v","bvb","vvtbvtvbttbttvvbvttbt","btbtbtttvbbtbttbtvvttbvtbtvtbvvtbbbb","bttbvbbttvvbtvvvvb","bvvbvbvttbvtbvvtbttvvvvtvbtvbttbbvvtvtvv","vbtttt","btbvbbbvbtvtbvvv","b","tbtbtv","vbvbbvvbvbbvvb","vbvvtvbvbvbttvbvbtvbtbtvtbvbbtb","bvvbvvttttttbtvvvttvbvtvvbvtbtvtbvttvvtbt","bvtbttv","bbbt","vtt","ttvv","b","vbb","vtvvbtttvtbbvvbbtbb","vvv","vvvvbbbtbbbvbbbb","ttvvbtvv","v","btvbbvtbbvbvtvttvvbbbtbvvvtbtb","vv","btbttbtbbvbvvbvttbttvtbbtbvtttvbbtbbtvtvvvvbbttvtvvbbbv","ttvbbbbttbtbtb","tvvbvbvvb","vv","ttbttvtvbtbbbbv","bvvvtbbvvvbtvbvtvtvvvvbb","vtbvvbvvvvvttvbbttbbvtvt","tbvbbt","b","v","tvbbtvvtvvtbtbttvvvb","tbttbttbbbtbtvtvtvtbbbvvtbbbvbbvvvbbttvvt","bbvttvtvvtbvbbttvbbtbvvttbvbvbtbvvvbbbvbvbvbtvbtvvvtvvtbttbttbbvbbbttvvvbvvtb","vttvvbvv","tbttbvvttvbtvvtbbvvv","bvbbbbbbbb","vtbvvtbbvtt","bvttbvvbvb","tvttttbbvvvbbtttvvv"};
        String sentence ="btbvtttttbvttbvvbbtvvbvbvvbtbtbtvbtttbvbbbtbbtbvvttbvbvtvbtbbttbvvbvbtttbvttbvvbbvvv";
        System.out.println(respace(dictionary,sentence));

    }
    public int respace(String[] dictionary, String sentence) {

        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(dictionary));
        list.sort((s1, s2) -> Integer.compare(s2.length(), s1.length()));
        for (String s : list) {
            sentence =sentence.replaceAll(s,"");
        }
        return sentence.length();


    }
    public int respace(Integer[] dictionary, String sentence) {

        /*List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(dictionary));
        list.sort((s1, s2) -> Integer.compare(s2.length(), s1.length()));
        for (String s : list) {
            sentence =sentence.replaceAll(s,"");
        }*/
        return sentence.length();


    }
}
