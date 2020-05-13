package com.ke.aboutleetcode;

import java.util.*;

public class _8 {

     public static int myAtoi(String str) {
        str =str.trim();
        if ("".equals(str)) {
            return 0;
        }

        boolean isNeagtive = false;
        long res = 0;
        int index = 0;

        if (!Character.isDigit(str.charAt(index)) && (str.charAt(index) == '-' || str.charAt(index) == '+')) {
            isNeagtive = str.charAt(index) == '-';
            index++;
        }

        // 找到第一个非0
        while (index <= str.length() - 1 && str.charAt(index) == '0'){
            index++;
        }

        while (index <= str.length() - 1 && Character.isDigit(str.charAt(index))) {
            res = res * 10 + (str.charAt(index ++) - '0');

            if (isNeagtive) {
                if (-res < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            }else if (res > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
        }

        return isNeagtive ? (int) - res : (int) res;
    }

    public static void main(String[] args) {
        System.out.println(_8.myAtoi("-2147483648"));
    }
}
