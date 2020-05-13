package com.ke.aboutleetcode;

public class _91_2 {

    public static int numDecodings(String s) {
        if (s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        if (s.length() == 1) {
            return 1;
        }

        int pre = 1, curr = 1;

        for (int i = 1; i < s.length(); i++) {
            int tmp = curr;

            if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2') {
                    curr = pre;
                } else {
                    return 0;
                }
            } else if (s.charAt(i - 1) == '1' ||
                    (s.charAt(i - 1) == '2' && s.charAt(i) >= '1' && s.charAt(i) <= '6')) {
                curr += pre;
            }

            pre = tmp;
        }

        return curr;
    }

    public static void main(String[] args) {
        String s = "1111";
        System.out.println(_91_2.numDecodings(s));
    }
}
