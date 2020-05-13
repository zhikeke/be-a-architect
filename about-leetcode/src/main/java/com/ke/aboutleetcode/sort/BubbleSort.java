package com.ke.aboutleetcode.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
 * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
 * 针对所有的元素重复以上的步骤，除了最后一个；
 * 重复步骤1~3，直到排序完成。
 */
public class BubbleSort {

    public static void sort(int[] arrays) {
        if (arrays == null || arrays.length < 2) {
            return;
        }

        for (int i = 0; i < arrays.length; i ++) {
            int start = 0;
            int end = arrays.length - i;

            while ((start + 1) < end) {
                if (arrays[start] > arrays[start + 1]) {
                    swap(arrays, start, start + 1);
                }

                start ++;
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
        BubbleSort.sort(arrays);
    }
}
