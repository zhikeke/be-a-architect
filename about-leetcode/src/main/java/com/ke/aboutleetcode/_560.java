package com.ke.aboutleetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _560 {
    public static int subarraySum(int[] nums, int k) {
        int count = 0, pre = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int num : nums) {
            pre += num;
            if (map.containsKey(pre - k)) {
                count += map.get(pre - k);
            }

            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{100,1,2,3,4};
        System.out.println(subarraySum(nums, 6));
    }
}
