package com.ke.aboutleetcode.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 桶排序
 * 设置一个定量的数组当作空桶；
 * 遍历输入数据，并且把数据一个一个放到对应的桶里去；
 * 对每个不是空的桶进行排序；
 * 从不是空的桶里把排好序的数据拼接起来
 */
public class BucketSort {

    public static void sort(int[] arrays) {
        if (arrays == null || arrays.length < 2) {
            return;
        }

        int minValue = arrays[0];
        int maxValue = arrays[0];

        for (int array : arrays) {
            if (array < maxValue) {
                minValue = array;
            } else if (array > maxValue) {
                maxValue = array;
            }
        }

        int DEFAULE_BUCKET_SIZE = 5;
        int bucketCount = (int) Math.floor((maxValue - minValue) / DEFAULE_BUCKET_SIZE) + 1;

        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int array : arrays) {
            buckets.get((int) (Math.floor(array - minValue) / DEFAULE_BUCKET_SIZE)).add(array);
        }


        List<Integer> result = new ArrayList<>();

        for (List<Integer> bucket : buckets) {
            if (bucket.size() > 0) {
                result.addAll(insertSort(bucket));
            }
        }

        System.out.println(result);
    }

    private static Collection<? extends Integer> insertSort(List<Integer> arrays) {
        if (arrays == null || arrays.size() < 2) {
            return arrays;
        }

        for (int i = 1; i < arrays.size(); i ++) {
            int curIndex = i;

            while (curIndex > 0) {
                if (arrays.get(curIndex) < arrays.get(curIndex - 1)) {
                    swap(arrays, curIndex, curIndex - 1);
                }

                curIndex --;
            }
        }

        return arrays;
    }

     private static void swap(List<Integer> arrays, int i, int j) {
        int temp = arrays.get(i);
        arrays.set(i, arrays.get(j));
        arrays.set(j, temp);
    }


    public static void main(String[] args) {
        int[] arrays = new int[]{2,3,1,3,2,4,6,7,9,2,19};
        BucketSort.sort(arrays);
    }

}
