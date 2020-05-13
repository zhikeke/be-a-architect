package com.ke.aboutleetcode;

import java.util.ArrayList;
import java.util.List;

public class _22 {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }

        generateParenthesis(0, 0, n, "", res);

        return res;
    }

    private void generateParenthesis(int leftCount, int rightCount, int n, String curStr, List<String> res) {
        if (leftCount >= n && rightCount >= n) {
            res.add(curStr);
            return;
        }

        if (leftCount < n) {
            generateParenthesis(leftCount + 1, rightCount, n, curStr + "(", res);
        }

        if (rightCount < leftCount) {
            generateParenthesis(leftCount, rightCount + 1, n , curStr + ")", res);
        }
    }

    public static void main(String[] args) {
        _22 solution = new _22();
        System.out.println(solution.generateParenthesis(3));
    }

}
