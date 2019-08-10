package com.blisskid.leetcode;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class ZigZag {

    public static void main(String[] args) {
        // write your code here
        ZigZag zz = new ZigZag();
        System.out.println(zz.convert("AB", 1));
    }

    public String convert(String s, int numRows) {
        if (numRows == 0) {
            return "";
        }
        if (numRows == 1) {
            return s;
        }
        int len = s.length();
        StringBuilder[] sbArray = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sbArray[i] = new StringBuilder();
        }
        int row = 0;
        int seq = 1;
        for (int i = 0; i < len; i++) {
            sbArray[row].append(s.charAt(i));
            row += seq;
            if (row == numRows - 1) {
                seq = -1;
            }
            if (row == 0) {
                seq = 1;
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            result.append(sbArray[i]);
        }
        return result.toString();
    }
}
