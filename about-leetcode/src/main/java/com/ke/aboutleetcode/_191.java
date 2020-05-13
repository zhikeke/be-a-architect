package com.ke.aboutleetcode;

public class _191 {
     public int hammingWeight(int n) {
         int count = 0;

         while (n != 0) {
             count ++;
             n &= (n - 1);
         }

         return count;
     }

    public static void main(String[] args) {
        _191 solution = new _191();
        System.out.println(solution.hammingWeight(2));
    }

}
