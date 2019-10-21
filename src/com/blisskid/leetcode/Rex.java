package com.blisskid.leetcode;

class Rex {
    enum Result {
        TRUE, FALSE
    }

    Result[][] memo;

    public boolean isMatchDpTopDown(String text, String pattern) {
        memo = new Result[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern);
    }

    public boolean dp(int i, int j, String text, String pattern) {
        if (memo[i][j] != null) {
            return memo[i][j] == Result.TRUE;
        }
        boolean ans;
        if (j == pattern.length()){
            ans = i == text.length();
        } else{
            boolean first_match = (i < text.length() &&
                    (pattern.charAt(j) == text.charAt(i) ||
                            pattern.charAt(j) == '.'));

            if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                ans = (dp(i, j+2, text, pattern) ||
                        first_match && dp(i+1, j, text, pattern));
            } else {
                ans = first_match && dp(i+1, j+1, text, pattern);
            }
        }
        memo[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }

    public static void main(String[] args) {
        Rex rex = new Rex();
        System.out.println(rex.isMatch("aaa", "ab*a*c*a"));
    }

    public boolean isMatchBottomUp(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }

    public boolean isMatchRecurse(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return (isMatch(text, pattern.substring(2)) ||
                    (first_match && isMatch(text.substring(1), pattern)));
        } else {
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }



    public boolean isMatch(String ss, String pp) {
        char[] s = ss.toCharArray();
        char[] p = pp.toCharArray();
        int sl = s.length, pl = p.length;
        //there is a array to record the status
        Boolean[][] f = new Boolean[sl][pl];
        //we want the f[sl -1][pl - 1];initialize all the f to false
        if (sl > 0 && pl > 0) {
            f[0][0] = s[0] == p[0] || p[0] == '.';
            int i = 1, j = 1;
            while(i < sl && j < pl) {
                if (p[j] == '*') {

                } else {
                    f[i][j] = f[i - 1][j - 1] && (s[i] == p[j] || p[j] == '.');
                    if (f[i][j]) {
                        i++;
                        j++;
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public boolean isMatch2(String s, String p) {
        //check parameters
        int sl = s.length(), pl = p.length();
        if (sl == 0 || pl == 0) {
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

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pl; i++) {
            if ((p.charAt(i) < 'a' || p.charAt(i) > 'z') && p.charAt(i) != '*' && p.charAt(i) != '.') {
                return false;
            }
            //remove all the continuous '*', only keep one '*'
            if (!(i > 0 && p.charAt(i) == '*' && p.charAt(i - 1) == '*')) {
                sb.append(p.charAt(i));
            }
        }

        p = sb.toString();
        pl = p.length();

        //compare the two string
        int si = 0, pi = 0;
        while (si < sl && pi < pl) {
            char cPi = p.charAt(pi);
            char cSi = s.charAt(si);
            if (cPi >= 'a' && cPi <= 'z') {
                if (cSi == cPi) {
                    si++;
                    pi++;
                } else if (pi + 1 < pl && p.charAt(pi + 1) == '*') {
                    pi++;
                    pi++;
                } else {
                    return false;
                }
            } else if (cPi == '.') {
                si++;
                pi++;
            } else if (cPi == '*') {
                return isMatch(s.substring(si), p.substring(pi - 1));
            }
        }
        if (si == sl) {
            if (pi == pl) {
                return true;
            } else {
                if (pi == pl - 1 && p.charAt(pi) == '*') {
                    return true;
                } else {
                    return false;
                }
            }

        } else {
            return false;
        }
    }
}
