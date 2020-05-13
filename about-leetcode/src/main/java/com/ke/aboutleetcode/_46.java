package com.ke.aboutleetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _46 {
     public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null ||  nums.length <= 0) {
            return res;
        }

         Set<Integer> set = new HashSet<>();
         for (int num : nums) {
             set.add(num);
         }

         helper(new ArrayList<>(), set, res, nums.length);

         return res;
    }

    private static void helper(List<Integer> curList, Set<Integer> set, List<List<Integer>> res, int length) {
         if (curList.size() == length) {
             res.add(new ArrayList<>(curList));
         }

        for (Integer integer : set) {
            Set<Integer> nSet = new HashSet<>(set);
            curList.add(integer);
            nSet.remove(integer);
            helper(curList, nSet, res, length);
            curList.remove(curList.size() - 1);
        }
    }


    public static void main(String[] args) {
         int[] nums = new int[]{1, 2, 3, 4, 5};
        System.out.println(_46.permute(nums).size());
    }
}
