package com.ke.aboutleetcode;

public class _5 {

    public static String longestPalindrome(String s) {
        if ("".equals(s)) {
            return s;
        }

         String res = s.substring(0, 1);
        int left = 0, right = 0;

        while (right <= s.length() - 1 && left <= right) {
            if (left == right) {
                left = 0;
                right ++;
                continue;
            }

            if (s.charAt(left) == s.charAt(right)) {
                if (isPalind(s,left + 1, right - 1)) {
                    if ((right - left + 1) > res.length()) {
                        res = s.substring(left, right + 1);
                    }
                }
            }

            left ++;
        }

        return res;
    }

    private static boolean isPalind(String s, int left, int right) {
        if (left >= right) {
            return true;
        }

        if (s.charAt(left) == s.charAt(right)) {
            return isPalind(s, left + 1, right - 1);
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(_5.longestPalindrome("cbbd"));
    }
}
