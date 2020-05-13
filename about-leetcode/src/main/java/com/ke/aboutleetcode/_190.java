package com.ke.aboutleetcode;

/**
 * >> 有符号右移动   1001 >> 2 ===> 1000
 * >>> 无符号右移 1001 >>> 2 ===> 0010
 * 记住对补码进行操作
 */
public class _190 {
    public int reverseBits(int n) {
        int res = 0;
        int i = 32;
        while (i -- > 0) {
            res |= ((n >>> (31 - i)) & 1) << i;
        }


        return res;
    }

    public static void main(String[] args) {
        _190 solution = new _190();
        System.out.println(solution.reverseBits(43261596));
    }
}
