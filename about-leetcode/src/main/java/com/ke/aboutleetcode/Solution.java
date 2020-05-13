package com.ke.aboutleetcode;

import javax.swing.tree.TreeNode;
import java.util.*;

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] A = new int[triangle.size() + 1];

        for(int i = triangle.size() - 1; i >= 0; i--) {
            for(int j = 0; j < triangle.get(i).size(); j++) {
                A[j] = Math.min(A[j], A[j+1]) + triangle.get(i).get(j);
            }
        }
        return A[0];
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] commands = new int[]{4,-1,4,-2,4};
        int[][] obstacles = new int[][]{
                {2},
                {3,4},
                {6,5,7},
                {4,1,8,3}

        };

        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3,4));
        triangle.add(Arrays.asList(6,5,7));
        triangle.add(Arrays.asList(4,1,8,3));

        System.out.println(solution.minimumTotal(triangle));;
    }
}
