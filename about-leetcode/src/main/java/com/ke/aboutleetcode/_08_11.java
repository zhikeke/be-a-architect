package com.ke.aboutleetcode;

public class _08_11 {

    public static int waysToChange(int n) {
        int[] conins = new int[] {1, 5, 10, 25};
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int conin : conins) {
            for (int i = conin; i <= n; i ++) {
                dp[i] = (dp[i] + dp[i - conin]) % 1000000007;
            }
        }

        return dp[n];
    }


    public static void main(String[] args) {
        System.out.println(_08_11.waysToChange(5));
    }
}
