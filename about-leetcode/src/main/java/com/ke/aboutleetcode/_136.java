package com.ke.aboutleetcode;

public class _136 {

    public static int singleNumber(int[] nums) {
        int res = 0;

        for (int num : nums) {
            res ^= num;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 1};
        System.out.println(_136.singleNumber(nums));
    }
}
