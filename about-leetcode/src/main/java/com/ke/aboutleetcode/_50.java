package com.ke.aboutleetcode;

public class _50 {

    public static double myPow(double x, int n) {
      if (x == 1 || n == 0) {
          return 1;
      }

      if (n < 0) {
          return 1 / helper(x, n);
      }

      return helper(x, n);
    }

    private static double helper(double x, int n) {
        if (x == 1 || n == 0) {
            return 1;
        }

        if (n % 2 != 0) {
            return helper(x, n / 2) * helper(x, n / 2) * x;
        }

        return helper(x, n / 2) * helper(x, n / 2);
    }

    public static void main(String[] args) {
        System.out.println(_50.myPow(2, -2));
    }
}
