package com.ke.aboutleetcode;

public class _200 {

    private int[] x = new int[]{0, -1, 0, 1};
    private int[] y = new int[]{-1, 0, 1, 0};

    public int numIslands(char[][] grid) {
        int count = 0;

        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return count;
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j ++) {
                if (grid[i][j] == '1') {
                    count ++;
                    grid[i][j] = '0';
                    helper(grid, i, j);
                }
            }
        }

        return count;
    }

    private void helper(char[][] grid, int i, int j) {
        for (int k = 0; k < 4; k ++) {
            if (i + x[k] >= 0 && i + x[k] < grid.length &&
                j + y[k] >= 0 && j + y[k] < grid[i].length) {
                if (grid[i + x[k]][j + y[k]] == '1') {
                    grid[i + x[k]][j + y[k]] = '0';
                    helper(grid, i + x[k], j + y[k]);
                }
            }
        }
    }

    public static void main(String[] args) {
         char[][] grid = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };

         _200 solution = new _200();
        System.out.println(solution.numIslands(grid));
    }
}
