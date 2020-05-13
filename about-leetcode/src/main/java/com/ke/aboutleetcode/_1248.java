package com.ke.aboutleetcode;

public class _1248 {
    public static int numberOfSubarrays(int[] nums, int k) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }

        if (k <= 0) {
            return nums.length + 1;
        }

        int left = 0, right = 0, oddCount = 0, res = 0;
        while (right < nums.length) {
            if ((nums[right ++] & 1) == 1) {
                oddCount ++;
            }

            if (oddCount == k) {
                int tmp = right;
                while (right < nums.length && (nums[right] & 1) == 0) {
                    right ++;
                }

                int rightEventCount = right - tmp + 1;
                int leftEventCount = 1;

                while ((nums[left] & 1) == 0) {
                    leftEventCount ++;
                    left ++;
                }

                res += leftEventCount * rightEventCount;

                left++;
                oddCount --;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2,1,1};
        System.out.println(numberOfSubarrays(nums, 3));
    }
}
