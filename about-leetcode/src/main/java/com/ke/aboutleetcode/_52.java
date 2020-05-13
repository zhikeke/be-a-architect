package com.ke.aboutleetcode;

import java.util.ArrayList;
import java.util.List;

public class _52 {
    private int total = 0;

    public int totalNQueens(int n) {
        if (n <= 0) {
            return total;
        }

        helper(n, 0, 0, 0, 0);

        return total;
    }

    private void helper(int count, int colNum, int col, int pie, int na) {
        if (colNum >= count) {
            total ++;
            return;
        }

        // 获取所有空位
        int dir = (~(col | pie | na)) & ((1 << count) - 1);

        while (dir > 0) {
            // 取最低为1
            int p = dir & -dir;

            // 在 p 位置上加入皇后
            dir = dir & (dir - 1);

            helper(count, colNum + 1, col | p, (pie | p) << 1, (na | p) >> 1);
        }

    }


    public static void main(String[] args) {
        _52 solution = new _52();
        System.out.println(solution.totalNQueens(4));
    }
}
