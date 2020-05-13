package com.ke.aboutleetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _36_2 {

    public boolean isValidSudoku(char[][] board) {
        boolean[][] rowBoolean = new boolean[9][9];
        boolean[][] colBoolean = new boolean[9][9];
        boolean[][] borderBoolean = new boolean[9][9];


        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j ++) {
                if (board[i][j] != '.') {
                    int border = (i / 3) * 3 + j / 3;
                    int val = board[i][j] - '1';

                    if (rowBoolean[i][val] || colBoolean[j][val] || borderBoolean[border][val]) {
                        return false;
                    }

                    rowBoolean[i][val] = true;
                    colBoolean[j][val] = true;
                    borderBoolean[border][val] = true;
                }
            }
        }

        return true;
    }

}
