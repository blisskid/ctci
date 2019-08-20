package com.blisskid.leetcode;

class Rex {
    public static void main(String[] args) {
        Rex rex = new Rex();
        System.out.println(rex.isMatch("aaa", "ab*a*c*a"));
    }

    public boolean isMatch(String s, String p) {
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
                char lastP = p.charAt(pi - 1);
                if (lastP >= 'a' && lastP <= 'z') {
                    si--;
                    pi++;
                    while (si < sl && pi < pl) {
                        if (s.charAt(si) == lastP) {
                            if (p.charAt(pi) == lastP || p.charAt(pi) == '.') {
                                pi++;
                                si++;
                            } else if (pi + 1 < pl && p.charAt(pi + 1) == '*') {
                                pi = pi + 2;
                            } else {
                                si++;
                            }

                        } else {
                            break;
                        }
                    }
                } else if (lastP == '.') {
                    if (pi == pl - 1) {
                        return true;
                    } else {
                        String rightLeftPStr = p.substring(pi + 1);
                        //check if rightLeftPStr match
                        for (int i = si - 1; i < sl; i++) {
                            if (isMatch(s.substring(i), rightLeftPStr)) {
                                return true;
                            }
                        }
                        return false;
                    }
                }
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
