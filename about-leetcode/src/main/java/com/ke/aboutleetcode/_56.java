package com.ke.aboutleetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _56 {
    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 0 || intervals[0].length < 2) {
            return intervals;
        }

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> res = new ArrayList<>();
        int l = intervals[0][0];
        int r = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
           if (intervals[i][0] <= r) {
                r = Math.max(intervals[i][1], r);
            } else {
                res.add(new int[]{l, r});
                l = intervals[i][0];
                r = intervals[i][1];
            }
        }

        res.add(new int[]{l, r});

        for (int[] re : res) {
            System.out.println(Arrays.toString(re));
        }

       return res.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{
                {1, 4},
                {0, 5},
                {3, 6}
        };

        _56.merge(intervals);
    }
}
