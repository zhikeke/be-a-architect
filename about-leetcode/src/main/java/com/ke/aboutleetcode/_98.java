package com.ke.aboutleetcode;

public class _98 {

    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        return helper(root.left, -1, root.val) && helper(root.right, root.val, -1);
    }

    private static boolean helper(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }

        if ((min != -1 && root.val <= min) || (max != -1 && root.val >= max)) {
            return false;
        }

        return helper(root.left, min, root.val) && helper(root.right, root.val, max);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(1);
//        node.right = new TreeNode(7);
//        node.right.left = new TreeNode(4);
//        node.right.right = new TreeNode(8);
        System.out.println(_98.isValidBST(node));
    }
}
