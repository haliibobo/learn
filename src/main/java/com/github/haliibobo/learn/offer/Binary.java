package com.github.haliibobo.learn.offer;

public class Binary {

    public static void main(String[] args) {
        int num = 10;
        int index = 2;
        //'&'有0就为0 都是1才是1
        System.out.println((num & (0x1 << index)) >> index);

        System.out.println(getNumberOf1(16));

        System.out.println(NumberOf1_Four(num));
    }

    /**
     * 返回num第index位上的二进制数值.
     * <<  左移运算符，num<<1,相当于乘以2
     * >>  右移运算符，num>>1,相当于除以2
     * >>> 无符号右移，忽略符号位，空位都以0补齐
     */
    public static int getBinaryValue(int num, int index) {
        return (num & (0x1 << index)) >> index;
    }

    /**
     * 如果一个整数不等于0，那么该整数的二进制表示中至少有一位是0。
     * 假设最后一位不是0，那么减去1，最后一位变成0而其他所有位都保持不变，也就是最后一位相当于做了取反操作，由1变成了0.
     * 假设最后一位是0，如果该整数的二进制表示中最右边的1位于第m位，那么减去1时，第m位由1变成0，而第m位之后的所有0都变成1，整数中第m位之前的所有位都保持不变。
     * TODO 把一个整数和它减去1的结果做位与运算，相当于把它最右边的1变成0。
     * 11000 - 1 -> 10111 11000 & 10111 -> 10000
     */
    private static int getNumberOf1(int num) {
        if (num == 0) {
            return 0;
        }
        int count = 0;
        while (num != 0) {
            num = num & (num - 1);
            count++;
        }
        return count;
    }

    //扩展：判断一个数是不是2的倍数
    //TODO 如果一个整数是2的整数次方，那么它的二进制表示中有且只有一位是1，
    private static boolean isTwoPow(int n) {
        if (n == 0 || n == 1 || n == -1) {
            return false;
        }
        n = n & (n - 1);
        return n == 0;
    }

    //扩展：输入两个整数m和n，计算需要改变m的二进制表示中的多少位才能得到n。
    //TODO 因为通过位运算的异或操作，可以得到两个数二进制的不同位有哪些（为1就是需要改变的）,因此做完异或操作，再统计1的个数
    private static int getNumberOfM(int num, int n) {
        int count = 0;
        int y = num ^ n;
        while (y != 0) {
            count++;
            y = y & (y - 0x1);
        }
        return count;
    }

    private static int NumberOf1_Four(int n) {
        return Integer.toBinaryString(n).replace("0","").length();
    }
}
