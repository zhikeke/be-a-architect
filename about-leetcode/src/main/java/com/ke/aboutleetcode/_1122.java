package com.ke.aboutleetcode;

import java.util.Arrays;

public class _1122 {
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        if (arr1 == null || arr1.length <= 1) {
            return arr1;
        }

        int max = arr1[0];
        for (int i : arr1) {
            max = Math.max(max, i);
        }

        int[] countArrays = new int[max + 1];
        for (int i : arr1) {
            countArrays[i] ++;
        }

        int index = 0;
        for (int i : arr2) {
            while (countArrays[i] -- > 0) {
                arr1[index ++] = i;
            }
        }

        for (int i = 0; i < countArrays.length; i++) {
            while (countArrays[i] -- > 0) {
                arr1[index ++] = i;
            }
        }

        return arr1;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = new int[]{2,1,4,3,9,6};

        System.out.println(Arrays.toString(_1122.relativeSortArray(arr1, arr2)));
    }

}
