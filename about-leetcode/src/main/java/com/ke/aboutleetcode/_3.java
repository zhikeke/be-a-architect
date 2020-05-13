package com.ke.aboutleetcode;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class _3 {

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() <= 0) {
            return 0;
        }

        int maxLen = 0;
        Queue<Character> noRepeatStack = new ArrayDeque<>();

         for (char c : s.toCharArray()) {
            if (!noRepeatStack.contains(c)) {
                noRepeatStack.add(c);
                maxLen = Math.max(maxLen, noRepeatStack.size());
            } else {
                while (noRepeatStack.peek() != c) {
                    noRepeatStack.poll();
                }

                noRepeatStack.poll();
                noRepeatStack.add(c);
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(_3.lengthOfLongestSubstring("pwwkew"));
    }
}
