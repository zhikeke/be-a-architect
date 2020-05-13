package com.ke.aboutleetcode.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 从数列中挑出一个元素，称为 “基准”（pivot）；
 * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 */
public class QuickSort {

    public static int[] sort(int[] arrays, int left, int right) {
        if (arrays == null || arrays.length < 2) {
            return arrays;
        }

        int partitionIndex;

        if (left < right) {
            partitionIndex = partition(arrays, left, right);
            sort(arrays, left, partitionIndex);
            sort(arrays, partitionIndex + 1, right);
        }

       return arrays;
    }

    private static int partition(int[] arrays, int left, int right) {
        int pivotValue = arrays[left];
        int index = left + 1;

        for (int i = left + 1; i < right; i ++) {
            if (arrays[i] <= pivotValue) {
                swap(arrays, i, index ++);
            }
        }

        swap(arrays, left, index - 1);
        return index - 1;
    }

     private static void swap(int[] arrays, int i, int j) {
        int temp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = temp;
    }

     public static void main(String[] args) {
        int[] arrays = new int[]{2,3,1,3,2,4,6,7,9,2,19};
         System.out.println(Arrays.toString(QuickSort.sort(arrays, 0, arrays.length)));
    }
}
