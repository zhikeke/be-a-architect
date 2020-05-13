package com.ke.aboutleetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _56_l {

    public static int[] singleNumbers(int[] nums) {
        int[] countArray = new int[65536];
        List<Integer> res = new ArrayList<>();

        for (int num : nums) {
            if (countArray[num] > 0) {
                countArray[num]--;
            } else {
                countArray[num]++;
            }
        }

        for (int i = 0; i < countArray.length; i++) {
            if (countArray[i] == 1) {
                res.add(i);
            }
        }

        return res.stream().mapToInt(Integer :: valueOf).toArray();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,10,4,1,4,3,3};
        System.out.println(Arrays.toString(_56_l.singleNumbers(nums)));
    }
}
