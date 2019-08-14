package com.blisskid.leetcode;

class Rex {
    public boolean isMatch(String s, String p) {
        //check parameters
        int sl = s.length(), pl = p.length();
        if (sl == 0 && pl == 0) {
            return true;
        } else if (sl == 0 && pl != 0) {
            return false;
        } else if (pl == 0 && sl != 0) {
            return false;
        }

        if (p.charAt(0) == '*') {
            return false;
        }

        for (int i = 0; i < sl; i++) {
            if (s.charAt(i) < 'a' || s.charAt(i) > 'z') {
                return false;
            }
        }

        for (int i = 0; i < pl; i++) {
            if ((p.charAt(i) < 'a' || p.charAt(i) > 'z') && (p.charAt(i) != '*' || p.charAt(i) != '.')) {
                return false;
            }
        }

        //compare the two string
        int si = 0, pi = 0;
        while (si < sl && pi < pl) {
            if (p.charAt(pi) == '.' || p.charAt(pi) == s.charAt(si)) {
                si++;
                pi++;
            } else if (p.charAt(pi) == '*') {
                //check if the char in s is the same as last char
                if (true) {

                }
                if (s.charAt(si) == s.charAt(si - 1)) {
                    si++;
                }
            }
        }
        return true;
    }
}
