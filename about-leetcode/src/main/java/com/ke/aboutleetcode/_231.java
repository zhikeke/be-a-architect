package com.ke.aboutleetcode;

public class _231 {
     public boolean isPowerOfTwo(int n) {
        if (n == 0) {
            return false;
        }

         long x = (long) n;
         return (x & (x - 1)) == 0;
    }

    public static void main(String[] args) {
        _231 solution = new _231();
        System.out.println(solution.isPowerOfTwo(4));
    }
}
