package com.ke.aboutleetcode;

public class _338_2 {
     public int[] countBits(int num) {
         int[] res = new int[num + 1];
         res[0] = 0;

         for (int i = 0; i <= num; i++) {
             if ((i & 1) == 1) {
                 res[i] = res[i - 1] + 1;
             } else {
                 res[i] = res[i / 2];
             }
         }

         return res;
     }

      public static void main(String[] args) {
        _338_2 solution = new _338_2();
        System.out.println(solution.countBits(2));
    }

}
