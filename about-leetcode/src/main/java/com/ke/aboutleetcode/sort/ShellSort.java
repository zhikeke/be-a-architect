package com.ke.aboutleetcode.sort;

import java.util.Arrays;

/**
 * 希尔排序
 * 选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
 * 按增量序列个数k，对序列进行k 趟排序；
 * 每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。
 * 仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
 */
public class ShellSort {
    private static void sort(int[] arrays) {
        if (arrays == null || arrays.length < 2) {
            return;
        }

        int len = arrays.length;

        for (int gap = (int) Math.floor(len / 2); gap > 0; gap = gap / 2) {
            for (int i = gap; i < len; i ++) {
                int j = i;
                int current = arrays[i];
                while (j - gap >= 0 && current < arrays[j - gap]) {
                    arrays[j] = arrays[j - gap];
                    j -= gap;
                }

                arrays[j] = current;
            }
        }

        System.out.println(Arrays.toString(arrays));
    }

    public static void main(String[] args) {
        int[] arrays = new int[]{2,3,1,3,2,4,6,7,9,2,19};
        ShellSort.sort(arrays);
    }

}
