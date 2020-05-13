package com.ke.aboutleetcode;

public class _242 {
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] charArrays = new int[26];

        for (char c : s.toCharArray()) {
            charArrays[c - 'a'] ++;
        }

        for (char c : t.toCharArray()) {
            charArrays[c - 'a'] --;
        }

        for (int charArray : charArrays) {
            if (charArray != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(_242.isAnagram("anagram", "nagaram"));
    }
}
