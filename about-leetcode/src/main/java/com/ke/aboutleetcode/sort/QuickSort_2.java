package com.ke.aboutleetcode.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 从数列中挑出一个元素，称为 “基准”（pivot）；
 * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 */
public class QuickSort_2 {

    public static int[] sort(int[] arrays, int left, int right) {
        if (left >= right) {
            return arrays;
        }

        int low = left;
        int height = right;
        int pivotValue = arrays[left];

        while (left < right) {
            while (left < right && arrays[right] >= pivotValue) {
                right --;
            }

            arrays[left] = arrays[right];

            while (left < right && arrays[left] <= pivotValue) {
                left ++;
            }

            arrays[right] = arrays[left];
        }

        arrays[left] = pivotValue;
        sort(arrays, low, left - 1);
        sort(arrays, left + 1, height);

       return arrays;
    }


     public static void main(String[] args) {
        int[] arrays = new int[]{2,3,1,3,2,4,6,7,9,2,19};
         System.out.println(Arrays.toString(QuickSort_2.sort(arrays, 0, arrays.length - 1)));
    }
}
