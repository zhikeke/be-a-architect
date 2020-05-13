package com.ke.aboutleetcode;

import java.util.*;

/**
 * 优先队列方法
 */
public class _1091 {
    /**
     * 定义节点类
     */
    class Node {
        int i;
        int j;

        public Node() {
        }

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }


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


        // 定义优先队列，距离最后一个节点距离越短的优先级越高
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            int distinctO1 = Math.abs(o1.i - distinctI) + Math.abs(o1.j - distinctJ);
            int distinctO2 = Math.abs(o2.i - distinctI) + Math.abs(o2.j - distinctJ);

            return distinctO1 - distinctO2;
        });


        pq.add(new Node(0, 0));
        cGrid[0][0] = 1;


        while (!pq.isEmpty()) {
            Queue<Node> tempPQ = new PriorityQueue<>((o1, o2) -> {
                int distinctO1 = Math.abs(o1.i - distinctI) + Math.abs(o1.j - distinctJ);
                int distinctO2 = Math.abs(o2.i - distinctI) + Math.abs(o2.j - distinctJ);

                return distinctO1 - distinctO2;
            });

            int size = pq.size();
            while (size-- > 0) {
                Node node = pq.poll();

                if (node.i == distinctI && node.j == distinctJ) {
                    return step + 1;
                }

                // 遍历8个方位
                for (int i = 0; i < 8; i++) {
                    if (x[i] + node.i >= 0 && x[i] + node.i < grid.length &&
                            y[i] + node.j >= 0 && y[i] + node.j < grid[0].length) {
                        if (grid[x[i] + node.i][y[i] + node.j] == 0 && cGrid[x[i] + node.i][y[i] + node.j] == 0) {
                            tempPQ.add(new Node(x[i] + node.i, y[i] + node.j));
                            cGrid[x[i] + node.i][y[i] + node.j] = 1;
                        }
                    }
                }
            }

            pq = tempPQ;
            step++;
        }

        return -1;
    }

    public static void main(String[] args) {
        _1091 solution = new _1091();
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
