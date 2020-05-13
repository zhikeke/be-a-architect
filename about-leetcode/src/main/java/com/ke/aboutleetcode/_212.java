package com.ke.aboutleetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _212 {
     public List<String> findWords(char[][] board, String[] words) {
        List<String> hadWords = new ArrayList<>();

        if (board == null || board.length <= 0 || words == null || words.length <= 0) {
            return hadWords;
        }

        Map<Character, List<String>> cMap = new HashMap<>();

        for (String word : words) {
            char startWord = word.charAt(0);
            if (cMap.containsKey(startWord)) {
                cMap.get(startWord).add(word);
            } else {
                List<String> array = new ArrayList<>();
                array.add(word);
                cMap.put(startWord, array);
            }
        }


        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[i].length; j ++) {
                 if (cMap.containsKey(board[i][j])) {
                     int finalI = i;
                     int finalJ = j;
                     char curWord = board[i][j];
                     board[i][j] = '*';
                     cMap.get(curWord).forEach(x ->{
                         helper(board, x, 1, finalI, finalJ, hadWords);
                     });
                     board[i][j] = curWord;
                 }
            }
        }


        return hadWords;
    }

    private void helper(char[][] board, String word, int curIndex,
                            int cutI, int curJ, List<String> hadWords) {
        int[] x = new int[]{0, -1, 0, 1};
        int[] y = new int[]{-1, 0, 1, 0};

        if (curIndex == word.length()) {
            if (!hadWords.contains(word)) {
                hadWords.add(word);
            }
            return;
        }

        char curWord = word.charAt(curIndex);
        for (int i = 0; i < x.length; i ++) {
            if (cutI + x[i] >= 0 && cutI + x[i] < board.length &&
                curJ + y[i] >= 0 && curJ + y[i] < board[cutI].length) {
                if (board[cutI + x[i]][curJ + y[i]] == curWord) {
                    board[cutI + x[i]][curJ + y[i]] = '*';
                    helper(board, word, curIndex + 1, cutI + x[i], curJ + y[i], hadWords);
                    board[cutI + x[i]][curJ + y[i]] = curWord;
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };

        String[] words = new String[]{"oath","pea","eat","rain"};

        _212 solution = new _212();

        System.out.println(solution.findWords(board, words));
    }
}
