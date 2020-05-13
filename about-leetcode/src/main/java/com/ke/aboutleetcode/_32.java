package com.ke.aboutleetcode;

import java.util.Stack;

public class _32 {

     public static int longestValidParentheses(String s) {
        if (s.length() <= 0) {
            return 0;
        }

        Stack<Integer> cStack = new Stack<>();
        cStack.push(-1);
        int maxCount = 0;

         for (int i = 0; i < s.toCharArray().length; i++) {
             if (s.charAt(i) == '(') {
                 cStack.push(i);
             } else {
                 cStack.pop();
                 if (cStack.isEmpty()) {
                     cStack.push(i);
                 } else {
                     maxCount = Math.max(maxCount, i - cStack.peek());
                 }
             }
         }

         return maxCount;
    }

    public static void main(String[] args) {
        String s = "()(()";

        System.out.println(_32.longestValidParentheses(s));
    }
}
