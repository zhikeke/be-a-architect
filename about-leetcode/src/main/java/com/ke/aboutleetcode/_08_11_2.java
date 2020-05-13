package com.ke.aboutleetcode;

public class _08_11_2 {
    private static int[] conins = new int[] {1, 5, 10, 25};
    private static int res = 0;

    public static int waysToChange(int n) {
       helper(n);
       return res;
    }

    private static void helper(int remain) {
        System.out.println(res);
        if (remain <= 0) {
            return;
        }

        for (int conin : conins) {
            if (remain - conin >= 0) {
                if (remain == conin) {
                    res ++;
                }

                helper(remain - conin);
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(_08_11_2.waysToChange(5));
    }
}
