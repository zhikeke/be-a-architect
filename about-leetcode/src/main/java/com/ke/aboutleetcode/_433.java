package com.ke.aboutleetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _433 {
     public int minMutation(String start, String end, String[] bank) {
         if (start.equals(end)) {
             return 0;
         }

         Set<String> bankSet = new HashSet<>(Arrays.asList(bank));

         if (!bankSet.contains(end)) {
             return -1;
         }

         Set<String> startSet = new HashSet<>(),
                     endSet = new HashSet<>();

         startSet.add(start);
         endSet.add(end);

         bankSet.remove(start);
         bankSet.remove(end);

         int level = 0;
         char[] replaceChar = new char[]{'A', 'C', 'G', 'T'};

         while (!startSet.isEmpty() && !endSet.isEmpty()) {
             if (startSet.size() > endSet.size()) {
                 Set<String> t = endSet;
                 endSet = startSet;
                 startSet = t;
             }

             Set<String> temp = new HashSet<>();

             for (String str : startSet) {
                 if (endSet.contains(str)) {
                     return level + 1;
                 }

                 char[] strChar = str.toCharArray();
                 for (int i = 0; i < strChar.length; i++) {
                     char cur = strChar[i];

                     for (char c : replaceChar) {
                         strChar[i] = c;
                         String newStr = new String(strChar);

                         if (endSet.contains(newStr)) {
                             return level + 1;
                         }

                         if (bankSet.remove(newStr)) {
                             temp.add(newStr);
                         }
                     }

                     strChar[i] = cur;
                 }
             }

             level++;
             startSet = temp;
         }

         return -1;
     }

    public static void main(String[] args) {
        _433 solution = new _433();
        String[] bank = new String[]{"AAAACCCC", "AAACCCCC", "AACCCCCC"};

        System.out.println(solution.minMutation("AAAAACCC", "AACCCCCC", bank));
    }

}
