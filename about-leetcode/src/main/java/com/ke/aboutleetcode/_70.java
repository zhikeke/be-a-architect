package com.ke.aboutleetcode;

public class _70 {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int[] nums = new int[n];
        nums[0] = 1;
        nums[1] = 2;

        for (int i = 2; i < n; i++) {
            nums[i] = nums[i - 1] + nums[i - 2];
        }

        return nums[n - 1];
    }

    public static void main(String[] args) {
        _70 solution = new _70();
        System.out.println(solution.climbStairs(5));
    }
}
