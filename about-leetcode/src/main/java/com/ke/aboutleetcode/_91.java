package com.ke.aboutleetcode;

public class _91 {

    public static int numDecodings(String s) {
        if (s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        if (s.length() == 1) {
            return 1;
        }

        int[] dp = new int[s.length()];
        dp[0] = 1;

        if (s.charAt(1) == '0') {
            if (s.charAt(0) > '2') {
                return 0;
            } else {
                dp[1] = 1;
            }
        } else {
            if (s.charAt(0) == '1' || (s.charAt(0) == '2' && s.charAt(1) <= '6')) {
                dp[1] = 2;
            } else {
                dp[1] = 1;
            }
        }


        for (int i = 2; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2') {
                    dp[i] = dp[i - 2];
                } else {
                    return 0;
                }
            } else if (s.charAt(i - 1) == '1' ||
                    (s.charAt(i - 1) == '2' && s.charAt(i) >= '1' && s.charAt(i) <= '6')) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }

        return dp[s.length() - 1];
    }

    public static void main(String[] args) {
        String s = "12120";
        System.out.println(_91.numDecodings(s));
    }
}
