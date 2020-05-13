package com.ke.aboutleetcode;

import java.util.Arrays;

public class _300_2 {

     public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }

        // 用户记录长度为 k+1 的子序列尾部元素的值
        int[] tails = new int[nums.length];
        int len = 0;

        tails[len] = nums[len];

         for (int i = 1; i < nums.length; i++) {
             // 当前元素比尾节点元素大，则从尾部加入当前元素
             if (nums[i] > tails[len]) {
                 tails[++len] = nums[i];
             } else {
                 // 从 tails[0 ... len] 找到第一个元素 使 tail[k] < nums[i] < tail[k + 1]
                 int left = 0, right = len;

                 while (left < right) {
                     int mid = (left + right) / 2;
                     if (tails[mid] < nums[i]) {
                         left = mid + 1;
                     } else {
                         right = mid;
                     }
                 }

                 tails[left] = nums[i];
             }
         }

         return len + 1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,10,4,3,8,9};
        System.out.println(_300_2.lengthOfLIS(nums));
    }
}
