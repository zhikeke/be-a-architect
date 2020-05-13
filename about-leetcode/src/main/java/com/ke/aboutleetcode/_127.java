package com.ke.aboutleetcode;

import java.util.*;

public class _127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordLists) {
        Set<String> wordSet = new HashSet<>(wordLists);

        if (beginWord.equals(endWord)) {
            return 1;
        }

        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Queue<String> childrens = new LinkedList<>();
        childrens.offer(beginWord);
        wordSet.remove(beginWord);

        int level = 1;

        while (!childrens.isEmpty()) {
            int size = childrens.size();

            for (int i = 0; i < size; i++) {
                String str = childrens.poll();
                assert str != null;
                if (str.equals(endWord)) {
                    return level;
                }

                for(String neighbor : neighbors(str,wordSet)){
                    childrens.offer(neighbor);
                }
            }

            level ++;
        }

        return 0;
    }

    private List<String> neighbors(String word, Set<String> wordSet) {
        List<String> strList = new ArrayList<>();

        for (int i = 0; i < word.toCharArray().length; i++) {
            char [] chars = word.toCharArray();

            for (char t = 'a'; t <= 'z'; t ++) {
                chars[i] = t;
                String newWord = new String(chars);
                if (wordSet.remove(newWord)) {
                    strList.add(newWord);
                }
            }
        }

        return strList;
    }

    public static void main(String[] args) {
        _127 solution = new _127();
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println(solution.ladderLength("hit", "dog", wordList));
    }
}
