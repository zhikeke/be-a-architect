package com.ke.aboutleetcode;

public class _983 {
    public static int mincostTickets(int[] days, int[] costs) {
        int[] dp = new int[days[days.length - 1] + 31];
        int maxDay = days[days.length - 1];
        int minDay = days[0];

        for (int d = maxDay, i = days.length - 1; d >= minDay; d --) {
            if (d == days[i]) {
                dp[d] = Math.min(dp[d + 1] + costs[0], Math.min(dp[d + 7] + costs[1], dp[d + 30] + costs[2]));
                i --;
            } else {
                dp[d] = dp[d + 1];
            }
        }

        return dp[minDay];
    }

    public static void main(String[] args) {
        int[] days = new int[]{1,4,6,7,8,20};
        int[] costs = new int[]{2, 7, 15};

        System.out.println(_983.mincostTickets(days, costs));
    }
}
