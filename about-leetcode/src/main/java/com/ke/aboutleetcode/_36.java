package com.ke.aboutleetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _36 {

    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Set<Character>> rowMap = new HashMap<>();
        Map<Integer, Set<Character>> colMap = new HashMap<>();
        Map<Integer, Set<Character>> borderMap = new HashMap<>();

        for (int i = 0; i < 9; i ++) {
            rowMap.put(i, new HashSet<>());
            colMap.put(i, new HashSet<>());
            borderMap.put(i, new HashSet<>());
        }


        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j ++) {
                if (board[i][j] != '.') {
                    int border = (i / 3) * 3 + j / 3;

                    if (rowMap.get(i).contains(board[i][j]) ||
                            colMap.get(j).contains(board[i][j]) ||
                            borderMap.get(border).contains(board[i][j])) {
                        return false;
                    }

                    rowMap.get(i).add(board[i][j]);
                    colMap.get(j).add(board[i][j]);
                    borderMap.get(border).add(board[i][j]);
                }
            }
        }

        return true;
    }

}
