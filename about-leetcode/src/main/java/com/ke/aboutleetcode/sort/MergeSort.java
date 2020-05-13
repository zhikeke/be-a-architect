package com.ke.aboutleetcode.sort;

import java.util.Arrays;

/**
 * 归并排序
 * 把长度为n的输入序列分成两个长度为n/2的子序列；
 * 对这两个子序列分别采用归并排序；
 * 将两个排序好的子序列合并成一个最终的排序序列。
 */
public class MergeSort {

    public static int[] sort(int[] arrays) {
        if (arrays == null || arrays.length < 2) {
            return arrays;
        }

        int mid = arrays.length / 2;
        int[] left = Arrays.copyOfRange(arrays, 0, mid);
        int[] right = Arrays.copyOfRange(arrays, mid, arrays.length);

        return merge(sort(left), sort(right));
    }

    private static int[] merge(int[] left, int[] right) {
        int[] arrays = new int[left.length + right.length];

        int arrayIndex = 0;
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] > right[rightIndex]) {
                arrays[arrayIndex ++] = right[rightIndex ++];
            } else {
                arrays[arrayIndex ++] = left[leftIndex ++];
            }
        }

        while (leftIndex < left.length) {
             arrays[arrayIndex ++] = left[leftIndex ++];
        }

        while (rightIndex < right.length) {
            arrays[arrayIndex ++] = right[rightIndex ++];
        }

        return arrays;
    }


    public static void main(String[] args) {
        int[] arrays = new int[]{2,3,1,3,2,4,6,7,9,2,19};
        System.out.println(Arrays.toString(MergeSort.sort(arrays)));
    }
}
