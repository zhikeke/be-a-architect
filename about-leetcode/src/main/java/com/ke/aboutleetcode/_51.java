package com.ke.aboutleetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _51 {
    List<List<String>> res;
    Set<Integer> lSet;
    Set<Integer> pieSet;
    Set<Integer> naSet;

     public List<List<String>> solveNQueens(int n) {
         res = new ArrayList<>();

        if (n == 0 ){
            return  res;
        }

        lSet = new HashSet<>();
        pieSet = new HashSet<>();
        naSet = new HashSet<>();

        helper(new ArrayList<>(), n,0);
        return res;
     }

    private void helper(List<String> curList, int count, int colNum) {
         if (colNum == count) {
             res.add(new ArrayList<>(curList));
             return;
         }

         for (int i = 0; i < count; i ++) {
           if (!lSet.contains(i) && !pieSet.contains(i - colNum) && !naSet.contains(i + colNum)) {
               curList.add(generateQueens(i, count));
               lSet.add(i);
               pieSet.add(i - colNum);
               naSet.add(i + colNum);

               helper(curList, count, colNum + 1);

               curList.remove(colNum);
               lSet.remove(i);
               pieSet.remove(i - colNum);
               naSet.remove(i + colNum);
           }
        }
    }

    /**
     * 生成皇后
     */
    private String generateQueens(int queneIndex, int count) {
        StringBuilder hql = new StringBuilder("");
        for (int i = 0; i < count; i++) {
            hql.append(".");
        }

        hql.setCharAt(queneIndex, 'Q');
        return hql.toString();
    }

    public static void main(String[] args) {
        _51 solution = new _51();
        System.out.println(solution.solveNQueens(4));
    }

}
