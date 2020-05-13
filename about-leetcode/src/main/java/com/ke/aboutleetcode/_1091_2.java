package com.ke.aboutleetcode;


import java.util.HashSet;
import java.util.Set;

public class _1091_2 {
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null || grid.length == 0 ||
                grid[0].length == 0 || grid[0][0] == 1 ||
                grid[grid.length - 1][grid[0].length - 1] == 1) {
            return -1;
        }

        int distinctI = grid.length - 1;
        int distinctJ = grid[0].length - 1;
        int[] x = new int[]{-1, 1, 0, 0, -1, 1, -1, 1};
        int[] y = new int[]{0, 0, -1, 1, 1, 1, -1, -1};
        int step = 0;
        int[][] cGrid = new int[grid.length][grid[0].length];

        Set<String> startSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();

        cGrid[0][0] = 1;
        cGrid[distinctI][distinctJ] = 1;
        startSet.add(0 + ":" + 0);
        endSet.add(distinctI + ":" + distinctJ);

        while (!startSet.isEmpty() && !endSet.isEmpty()) {
            if (startSet.size() > endSet.size()) {
                Set<String> temp = endSet;
                endSet = startSet;
                startSet = temp;
            }

            Set<String> tempSet = new HashSet<>();

            for (String node : startSet) {
                if (endSet.contains(node)) {
                    return step + 1;
                }

                int nodeI = Integer.parseInt(node.substring(0, node.indexOf(":")));
                int nodeJ = Integer.parseInt(node.substring(node.indexOf(":") + 1));

                 // 遍历8个方位
                for (int i = 0; i < 8; i++) {
                    if (x[i] + nodeI >= 0 && x[i] + nodeI < grid.length &&
                            y[i] + nodeJ >= 0 && y[i] + nodeJ < grid[0].length) {
                        String newNode = (x[i] + nodeI) + ":" + (y[i] + nodeJ);

                        if (endSet.contains(newNode)) {
                            return step + 2;
                        }

                        if (grid[x[i] + nodeI][y[i] + nodeJ] == 0 && cGrid[x[i] + nodeI][y[i] + nodeJ] == 0) {
                            tempSet.add(newNode);
                            cGrid[x[i] + nodeI][y[i] + nodeJ] = 1;
                        }
                    }
                }
            }

            startSet = tempSet;
            step ++;
        }

        return -1;
    }

     public static void main(String[] args) {
        _1091_2 solution = new _1091_2();
        int[][] grid = new int[][]{
                {0, 1, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1, 1},
                {0, 1, 0, 0, 0, 1},
                {1, 0, 0, 1, 0, 1},
                {0, 0, 1, 0, 1, 0}
        };

        System.out.println(solution.shortestPathBinaryMatrix(grid));
    }
}
