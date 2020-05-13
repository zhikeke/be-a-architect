package com.ke.aboutleetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 双向BFS
 */
public class _127_2 {
    public int ladderLength(String beginWord, String endWord, List<String> wordLists) {
        if (!wordLists.contains(endWord)) {
            return 0;
        }

        Set<String> beginSet = new HashSet<>(),
                    endSet = new HashSet<>();

        Set<String> wordSet = new HashSet<>(wordLists);
        wordSet.remove(beginWord);
        wordSet.remove(endWord);

        beginSet.add(beginWord);
        endSet.add(endWord);
        int level = 1;

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // beginSet 总维护最小的集合
            if (beginSet.size() > endSet.size()) {
                Set<String> tmpSet = endSet;
                endSet = beginSet;
                beginSet = tmpSet;
            }

            Set<String> temp = new HashSet<>();
            for (String curWord : beginSet) {
                if (endSet.contains(curWord)) {
                    return level + 1;
                }

                char[] chars = curWord.toCharArray();

                for (int i = 0; i < chars.length; i++) {
                    char curChar = chars[i];

                    for (char c = 'a'; c <= 'z'; c ++) {
                        chars[i] = c;
                        String newStr = new String(chars);

                        if (endSet.contains(newStr)) {
                            return level + 1;
                        }

                        if (wordSet.remove(newStr)) {
                            temp.add(newStr);
                        }
                    }

                    chars[i] = curChar;
                }
            }

            beginSet = temp;
            level ++;
        }

        return 0;
    }

    public static void main(String[] args) {
        _127_2 solution = new _127_2();
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log");
        System.out.println(solution.ladderLength("hit", "dog", wordList));
    }
}
