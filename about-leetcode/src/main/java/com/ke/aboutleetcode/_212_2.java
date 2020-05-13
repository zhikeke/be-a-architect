package com.ke.aboutleetcode;

import java.util.ArrayList;
import java.util.List;

public class _212_2 {
     private int[] x = new int[]{0, -1, 0, 1};
     private int[] y = new int[]{-1, 0, 1, 0};

    class Trie {
        private char val;
        private String curWord;
        public boolean isWord;
        public Trie[] childrens = new Trie[26];

        public Trie () {

        }

        public Trie(char word) {
            Trie trie = new Trie();
            trie.childrens = new Trie[26];
            trie.val = word;
        }
     }


    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();

        if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) {
            return res;
        }

        Trie root = getTrieRoot(words);

        for (int i = 0; i < board.length; i++) {
            for (int j = board[i].length - 1; j >= 0; j--) {
                dfs(root, board, i , j, res);
            }
        }

        return res;
    }

    private void dfs(Trie root, char[][] board, int i, int j, List<String> res) {
        char c = board[i][j];
        if (board[i][j] == '*' || root.childrens[board[i][j] - 'a'] == null) {
            return;
        }

        root = root.childrens[board[i][j] - 'a'];

        if (root.isWord) {
            res.add(root.curWord);
            root.curWord = null;
            root.isWord = false;
        }

        board[i][j] = '*';

        for (int k = 0; k < x.length; k ++) {
            if (i + x[k] >= 0 && i + x[k] < board.length &&
                    j + y[k] >= 0 && j + y[k] < board[i].length) {
                dfs(root, board, i + x[k], j + y[k], res);
            }
        }

        board[i][j] = c;
    }

    /**
     * 根据单词构建字典树并返回跟节点
     * @param words
     * @return
     */
    private Trie getTrieRoot(String[] words) {
        Trie root = new Trie(' ');

        for (String word : words) {
            Trie curRoot = root;
            for (char c : word.toCharArray()) {
                if (curRoot.childrens[c - 'a'] == null) {
                    curRoot.childrens[c - 'a'] = new Trie(c);
                }

               curRoot = curRoot.childrens[c - 'a'];
            }

            curRoot.curWord = word;
            curRoot.isWord = true;
        }

        return root;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };

        String[] words = new String[]{"oath","pea","eat","rain"};

        _212_2 solution = new _212_2();

        System.out.println(solution.findWords(board, words));
    }
}
