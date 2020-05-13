package com.ke.aboutleetcode;

import java.util.*;

public class _199 {

    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Stack<TreeNode> newStack = new Stack<>();
            int stackSize = stack.size();

            for (int i = 0; i < stackSize; i++) {
                TreeNode curNode = stack.pop();
                if (i == 0) {
                    res.add(curNode.val);
                }

                if (curNode.right != null) {
                    newStack.add(curNode.right);
                }

                if (curNode.left != null) {
                    newStack.add(curNode.left);
                }
            }

            while (!newStack.isEmpty()) {
                stack.add(newStack.pop());
            }
        }

        return res;
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
