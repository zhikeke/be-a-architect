package com.ke.aboutleetcode;

public class _338 {
     public int[] countBits(int num) {
         int[] res = new int[num + 1];

         for (int i = 0; i <= num; i++) {
             res[i] = getCount(i);
         }

         return res;
    }

    private int getCount(int num) {
        int count = 0;
        while (num > 0) {
           num &= num - 1;
           count ++;
        }

        return count;
    }

    public static void main(String[] args) {
        _338 solution = new _338();
        System.out.println(solution.countBits(2));
    }

}
