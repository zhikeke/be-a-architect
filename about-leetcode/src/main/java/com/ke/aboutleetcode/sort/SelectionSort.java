package com.ke.aboutleetcode.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 初始状态：无序区为R[1..n]，有序区为空；
 * 第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。
 * 该趟排序从当前无序区中-选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
 * n-1趟结束，数组有序化了
 */
public class SelectionSort {

    public static void sort(int[] arrays) {
        if (arrays == null || arrays.length < 2) {
            return;
        }

        for (int i = 0; i < arrays.length - 1; i ++) {
            int minIndex = i;

            for (int j = i + 1; j < arrays.length; j ++) {
                if (arrays[minIndex] > arrays[j]) {
                    minIndex = j;
                }
            }

            swap(arrays, i, minIndex);
        }

        System.out.println(Arrays.toString(arrays));
    }

     private static void swap(int[] arrays, int i, int j) {
        int temp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = temp;
    }

    public static void main(String[] args) {
        int[] arrays = new int[]{2,3,1,3,2,4,6,7,9,2,19};
        SelectionSort.sort(arrays);
    }
}
