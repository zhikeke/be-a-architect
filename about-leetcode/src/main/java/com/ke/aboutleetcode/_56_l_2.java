package com.ke.aboutleetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _56_l_2 {

    public static int[] singleNumbers(int[] nums) {
        int k = 0;
        for (int num : nums) {
            k ^= num;
        }

        int mask = 1;
        while ((k & mask) == 0) {
            mask <<= 1;
        }

        int a = 0, b = 0;

        for (int num : nums) {
            if ((num & mask) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }

        return new int[]{a, b};
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,10,4,1,4,3,3};
        System.out.println(Arrays.toString(_56_l_2.singleNumbers(nums)));
    }
}
