package com.ke.aboutleetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _199_1 {
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        helper(root, 0, res);

        return res;
    }

    private static void helper(TreeNode root, int level, List<Integer> res) {
        if (root == null) {
            return;
        }

        if (res.size() == level) {
            res.add(root.val);
        }

        helper(root.right, level +1, res);
        helper(root.left, level + 1, res);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);

        System.out.println(rightSideView(root));
    }
}
