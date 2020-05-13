package com.ke.aboutleetcode;

public class _37 {
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        boolean[][] rowBoolean = new boolean[10][10];
        boolean[][] colBoolean = new boolean[10][10];
        boolean[][] borderBoolean = new boolean[10][10];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j ++ ) {
                if (board[i][j] != '.') {
                    int borderIndex = (i / 3) * 3 + j / 3;
                    int val = board[i][j] - '0';
                    rowBoolean[i][val] = true;
                    colBoolean[j][val] = true;
                    borderBoolean[borderIndex][val] = true;
                }
            }
        }

        helper(0, 0, board, rowBoolean, colBoolean, borderBoolean);
    }

    private boolean helper(int row, int col, char[][] board, boolean[][] rowBoolean, boolean[][] colBoolean, boolean[][] borderBoolean) {
        if (row == board.length) {
            return true;
        }

        int nextRow = col == board[row].length - 1 ? row + 1 : row;
        int nexCol = col == board[row].length - 1 ? 0 : col + 1;

       if (board[row][col] == '.') {
            int borderIndex = (row / 3) * 3 + col / 3;

            for (int z = 1; z <= 9; z ++) {
                if (!rowBoolean[row][z] && !colBoolean[col][z] && !borderBoolean[borderIndex][z]) {
                    board[row][col] = (char)('0' + z);
                    rowBoolean[row][z] = true;
                    colBoolean[col][z] = true;
                    borderBoolean[borderIndex][z] = true;

                    if (helper(nextRow, nexCol, board, rowBoolean, colBoolean, borderBoolean)) {
                        return true;
                    }

                    board[row][col] = '.';
                    rowBoolean[row][z] = false;
                    colBoolean[col][z] = false;
                    borderBoolean[borderIndex][z] = false;
                }
            }
        } else {
            return helper(nextRow, nexCol, board, rowBoolean, colBoolean, borderBoolean);
       }

       return false;
    }

    public static void main(String[] args) {
        char[][] board = new char[][] {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };

        _37 solution = new _37();
        solution.solveSudoku(board);
    }
}
