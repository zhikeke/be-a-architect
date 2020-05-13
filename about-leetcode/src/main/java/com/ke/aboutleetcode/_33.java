package com.ke.aboutleetcode;

public class _33 {

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }

        int low = 0, height = nums.length - 1;

        while (low <= height) {
            int mid = low + (height - low) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[low] <= nums[mid]) {
                if (nums[low] <= target &&  target < nums[mid]) {
                    height = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[height]) {
                    low = mid + 1;
                } else {
                    height = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,5,6,7,0,1,2};
        System.out.println(_33.search(nums, 5));
    }
}
