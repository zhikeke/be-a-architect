package com.ke.aboutleetcode.sort;

import java.util.Arrays;
import java.util.Collections;

/**
 * 计数排序
 * 找出待排序的数组中最大和最小的元素；
 * 统计数组中每个值为i的元素出现的次数，存入数组C的第i项；
 * 对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）；
 * 反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1
 */
public class CountingSort {

    public static void sort(int[] arrays) {
        if (arrays == null || arrays.length < 2) {
            return;
        }

        int MAX_VALUE = arrays[0];

        for (int array : arrays) {
            MAX_VALUE = Math.max(MAX_VALUE, array);
        }

        int[] countArrays = new int[MAX_VALUE + 1];

        for (int array : arrays) {
            countArrays[array] ++;
        }

        int index = 0;
        for (int i = 0; i < countArrays.length; i++) {
            if (countArrays[i] > 0) {
                for (int j = 0; j < countArrays[i]; j++) {
                    arrays[index++] = i;
                }
            }
        }

        System.out.println(Arrays.toString(arrays));
    }


    public static void main(String[] args) {
        int[] arrays = new int[]{2,3,1,3,2,4,6,7,9,2,19};
        CountingSort.sort(arrays);
    }
}
