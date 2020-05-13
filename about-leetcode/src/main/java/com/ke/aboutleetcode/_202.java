package com.ke.aboutleetcode;

public class _202 {

    public static boolean isHappy(int n) {
        if (n == 1) {
            return true;
        }

        int slow = n, fast = n;
        do {
            slow = squareSum(slow);
            fast = squareSum(squareSum(fast));
        } while (slow != fast);

        return slow == 1;
    }

    private static int squareSum(int n) {
        int newVal = 0;

        while (n > 0) {
            newVal += Math.pow(n % 10, 2);
            n /= 10;
        }

        return newVal;
    }

    public static void main(String[] args) {
        System.out.println(_202.isHappy(20));
    }
}
