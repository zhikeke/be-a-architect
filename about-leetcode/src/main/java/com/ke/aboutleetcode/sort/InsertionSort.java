package com.ke.aboutleetcode.sort;

import java.util.Arrays;

/**
 * 插入排序
 * 从第一个元素开始，该元素可以认为已经被排序；
 * 取出下一个元素，在已经排序的元素序列中从后向前扫描；
 * 如果该元素（已排序）大于新元素，将该元素移到下一位置；
 * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
 * 将新元素插入到该位置后；
 * 重复步骤2~5
 */
public class InsertionSort {

    private static void sort(int[] arrays) {
        if (arrays == null || arrays.length < 2) {
            return;
        }

        for (int i = 1; i < arrays.length; i ++) {
            int curIndex = i;

            while (curIndex > 0) {
                if (arrays[curIndex] < arrays[curIndex - 1]) {
                    swap(arrays, curIndex, curIndex - 1);
                }

                curIndex --;
            }
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
        InsertionSort.sort(arrays);
    }
}
