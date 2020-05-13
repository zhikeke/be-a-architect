package com.ke.aboutleetcode;

public class _5_1 {

     public static String longestPalindrome(String s) {
         if ("".equals(s) || s.length() == 0) {
             return s;
         }

         int start = 0, len = 0;

         for (int i = 0; i < s.length(); i ++) {
             int curLen = Math.max(getLen(s, i, i), getLen(s, i, i + 1));

             if (curLen > len) {
                 len = curLen;
                 start = i - (curLen - 1) / 2;
             }
         }

         return s.substring(start, start + len);
     }

      private static int getLen(String s, int left, int right) {
        while (left >= 0 && right <= s.length() - 1 && s.charAt(left) == s.charAt(right)) {
            left --;
            right ++;
        }

        return right - left - 1;
    }

     public static void main(String[] args) {
        System.out.println(_5_1.longestPalindrome("aaaa"));
    }
}
