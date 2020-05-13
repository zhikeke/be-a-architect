package com.ke.aboutleetcode;

public class _130 {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j ++) {
                if (i ==0 || i == board.length - 1 || j == 0 || j == board[0].length - 1) {
                    if (board[i][j] == 'O') {
                        helper(board, i , j);
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j ++) {
               if (board[i][j] == 'O') {
                   board[i][j] = 'X';
               } else if (board[i][j] == '#') {
                   board[i][j] = 'O';
               }
            }
        }
    }

    private void helper(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '#' || board[i][j] == 'X') {
            return;
        }

        board[i][j] = '#';

        helper(board, i - 1, j);
        helper(board, i + 1, j);
        helper(board, i, j - 1);
        helper(board, i, j + 1);
    }


    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'O', 'X', 'X', 'O', 'X'},
                {'X', 'O', 'O', 'X', 'O'},
                {'X', 'O', 'X', 'O', 'X'},
                {'O', 'X', 'O', 'O', 'O'},
                {'X', 'X', 'O', 'X', 'O'}
        };

        _130 solution = new _130();
        solution.solve(board);
    }
}
