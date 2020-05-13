package com.ke.aboutleetcode;

import java.util.ArrayList;
import java.util.List;

public class _51_2 {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }

        helper(res, new ArrayList<>(), n, 0, 0, 0, 0);

        return res;
    }

    private void helper(List<List<String>> res, List<String> curList, int count, int colNum, int col, int pie, int na) {
        if (colNum >= count) {
            res.add(new ArrayList<>(curList));
            return;
        }

        // 获取所有空位
        int dir = (~(col | pie | na)) & ((1 << count) - 1);

        while (dir > 0) {
            // 取最低为1
            int p = dir & -dir;

            // 在 p 位置上加入皇后
            dir = dir & (dir - 1);

            curList.add(generateQueens(p, count));

            helper(res, curList, count, colNum + 1, col | p, (pie | p) << 1, (na | p) >> 1);

            curList.remove(curList.size() - 1);
        }

    }

    /**
     * 生成皇后
     */
    private String generateQueens(int queneIndex, int count) {
        StringBuilder hql = new StringBuilder("");
        int pos = 0;

        for (int i = 0; i < count; i++) {
            hql.append(".");
        }

        while (queneIndex > 0) {
            queneIndex >>= 1;
            pos++;
        }

        hql.setCharAt(pos - 1, 'Q');
        return hql.toString();
    }

    public static void main(String[] args) {
        _51_2 solution = new _51_2();
        System.out.println(solution.solveNQueens(8));
    }
}
