package com.ke.aboutleetcode;

public class _547 {
    public int findCircleNum(int[][] M) {
        int count = 0;
        boolean[] visited = new boolean[M.length];

        if (M.length == 0 || M[0].length == 0) {
            return count;
        }

        for (int i = 0; i < M.length; i++) {
            if (!visited[i]) {
                dfs(M, visited, i);
                count++;
            }
        }

        return count;
    }

    private void dfs(int[][] m, boolean[] visited, int i) {
        for (int j = 0; j < m.length; j ++) {
            if (m[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(m, visited, j);
            }
        }

    }


    public static void main(String[] args) {
        int[][] M = new int[][]{
                {1,0,0,1},
                {0,1,1,0},
                {0,1,1,1},
                {1,0,1,1}
        };

         _547 solution = new _547();

        System.out.println(solution.findCircleNum(M));
    }
}
