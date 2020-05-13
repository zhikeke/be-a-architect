package com.ke.aboutleetcode;

import java.util.*;

public class _773 {
    class Node {
        int[] board;
        String boardStr;
        int curZeroIndex;

        Node () {}

        Node(int[] board, int curZeroIndex, String boardStr) {
            this.board = board;
            this.curZeroIndex = curZeroIndex;
            this.boardStr = boardStr;
        }
    }


    public int slidingPuzzle(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return 0;
        }

        int[] nBoard = new int[board.length * board[0].length];
        int zeroIndex = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j ++) {
                nBoard[(i * board[i].length) + j] = board[i][j];
                if (board[i][j] == 0) {
                    zeroIndex = (i * board[i].length) + j;
                }
            }
        }

        List<int[]> replaceList = new LinkedList<>();
        replaceList.add(new int[] {1, 3});
        replaceList.add(new int[] {0, 2, 4});
        replaceList.add(new int[] {1, 5});
        replaceList.add(new int[] {0, 4});
        replaceList.add(new int[] {1, 3, 5});
        replaceList.add(new int[] {2, 4});

        Set<String> visitBoardStr = new HashSet<>();
        Queue<Node> queue = new ArrayDeque<>();
        Node node = new Node(nBoard, zeroIndex, getBoardStr(nBoard));

        int step = 0;
        String aimBoardStr = "123450";
        visitBoardStr.add(getBoardStr(nBoard));
        queue.add(node);

        while (!queue.isEmpty()) {
            int size = queue.size();

            Queue<Node> newQueuee = new ArrayDeque<>();

            while (size-- > 0) {
                Node curNode = queue.poll();

                if (curNode.boardStr.equals(aimBoardStr)) {
                    return step;
                }

                int[] curBoard = curNode.board;
                int curZeroIndex = curNode.curZeroIndex;

                for (int canZeroIndex : replaceList.get(curZeroIndex)) {
                    int replaceBoardNum = curBoard[canZeroIndex];
                    curBoard[canZeroIndex] = 0;
                    curBoard[curZeroIndex] = replaceBoardNum;

                    String newBoardStr = getBoardStr(curBoard);

                    if (newBoardStr.equals(aimBoardStr)) {
                        return step + 1;
                    }

                    if (visitBoardStr.contains(newBoardStr)) {
                        curBoard[curZeroIndex] = 0;
                        curBoard[canZeroIndex] = replaceBoardNum;
                        continue;
                    }

                    visitBoardStr.add(newBoardStr);
                    int[] newBoard = Arrays.copyOf(curBoard, curBoard.length);
                    newQueuee.add(new Node(newBoard, canZeroIndex, newBoardStr));

                    curBoard[curZeroIndex] = 0;
                    curBoard[canZeroIndex] = replaceBoardNum;
                }
            }

            step ++;
            queue = newQueuee;
        }


        return -1;
    }

    private String getBoardStr(int[] board) {
        StringBuilder sb = new StringBuilder("");
        for (int i : board) {
            sb.append(i);
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        _773 solution = new _773();

        int[][] board = new int[][] {
                {3, 2, 4},
                {1, 5, 0}
        };

        System.out.println(solution.slidingPuzzle(board));
    }

}
