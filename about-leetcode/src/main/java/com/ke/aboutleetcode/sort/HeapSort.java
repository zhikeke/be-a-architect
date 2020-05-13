package com.ke.aboutleetcode.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapSort {
    public static void sort(int[] arrays) {
        if (arrays == null || arrays.length <= 1) {
            return;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o));

        for (int array : arrays) {
            pq.offer(array);
        }

        for (int i = 0; i < arrays.length; i++) {
            arrays[i] = pq.poll();
        }

        System.out.println(Arrays.toString(arrays));
    }

    public static void main(String[] args) {
        int[] arrays = new int[]{2,3,1,3,2,4,6,7,9,2,19};
        HeapSort.sort(arrays);
    }
}
