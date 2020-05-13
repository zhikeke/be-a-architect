package com.ke.aboutleetcode;

import java.util.Arrays;

public class _300 {

     public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int max = 1;

         for (int i = 1; i < nums.length; i++) {
             for (int j = 0; j < i; j ++) {
                 if (nums[i] > nums[j]) {
                     dp[i] = Math.max(dp[i], dp[j] +  1);
                     max = Math.max(max, dp[i]);
                 }
             }
         }

         return max;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10,9,2,5,3,7,101,18};
        System.out.println(_300.lengthOfLIS(nums));
    }
}
