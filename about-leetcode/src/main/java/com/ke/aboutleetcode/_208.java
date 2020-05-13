package com.ke.aboutleetcode;

public class _208 {
    class TrieNode {
        private char val;
        // 标识是否是单词
        private boolean isWord;
        private TrieNode[] childrens = new TrieNode[26];

        public TrieNode() {

        }

        public TrieNode(char word) {
            TrieNode trieNode = new TrieNode();
            trieNode.val = word;
            trieNode.isWord = false;
        }
    }

    class Trie {
        private TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new TrieNode(' ');
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            TrieNode curNode = root;

            for (char c : word.toCharArray()) {
                if (curNode.childrens[c - 'a'] == null) {
                    curNode.childrens[c - 'a'] = new TrieNode(c);
                }

                curNode = curNode.childrens[c - 'a'];
            }

            curNode.isWord = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            TrieNode curNode = root;

            for (char c : word.toCharArray()) {
                if (curNode.childrens[c - 'a'] != null) {
                    curNode = curNode.childrens[c - 'a'];
                    continue;
                }

                return  false;
            }

            return curNode.isWord;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            TrieNode curNode = root;

            for (char c : prefix.toCharArray()) {
                if (curNode.childrens[c - 'a'] != null) {
                    curNode = curNode.childrens[c - 'a'];
                    continue;
                }

                return  false;
            }

            return true;
        }
    }

    public static void main(String[] args) {
        _208 solution = new _208();
        _208.Trie trie = solution.new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));

        trie.insert("app");
        System.out.println(trie.search("app"));
    }
}


