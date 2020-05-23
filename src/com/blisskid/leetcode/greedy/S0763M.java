package com.blisskid.leetcode.greedy;

import java.util.*;

/**
 * A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.
 *
 * Example 1:
 *
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 * Note:
 *
 * S will have length in range [1, 500].
 * S will consist of lowercase letters ('a' to 'z') only.
 */

public class S0763M {

    private class LetterObj {
        public int startIndex = 0;
        public int endIndex = 1000;
        public Integer length = 0;
        public void calLength() {
            length = endIndex - startIndex + 1;
        }

        public int compareTo(LetterObj o1, LetterObj o2) {
            return o2.length.compareTo(o1.length);
        }
    }

    public List<Integer> partitionLabels(String S) {
        char[] s = S.toCharArray();
        LetterObj[] letters = new LetterObj[26];
        //find the distinct letter
        for (int i = 0; i < s.length; i++) {
            int index = s[i] - 'a';
            if (letters[index] == null) {
                letters[index] = new LetterObj();
                letters[index].startIndex = i;
                letters[index].endIndex = i;
            } else {
                if (i < letters[index].startIndex) {
                    letters[index].startIndex = i;
                    letters[index].calLength();
                } else if (i > letters[index].endIndex) {
                    letters[index].endIndex = i;
                    letters[index].calLength();
                }
            }
        }
        Arrays.sort(letters);
        for (int i = 0; i < letters.length - 1; i++) {
            if (letters[i] != null) {
                for (int j = i + 1; j < letters.length; j++) {
                    if (letters[j] != null) {
                        if (letters[j].startIndex < letters[i].startIndex) {
                            if (letters[j].endIndex >= letters[i].startIndex) {
                                letters[i].startIndex = letters[j].startIndex;
                                letters[i].calLength();
                                letters[j] = null;
                            }
                        } else if (letters[j].startIndex >= letters[i].startIndex && letters[j].startIndex <= letters[i].endIndex) {
                            if (letters[j].endIndex <= letters[i].endIndex) {
                                letters[j] = null;
                            } else {
                                letters[i].endIndex = letters[j].endIndex;
                                letters[i].calLength();
                                letters[j] = null;
                            }
                        } else {

                        }
                    }
                }
            }
        }
        return null;
    }

    /*
    public List<Integer> partitionLabels1(String S) {
        char[] s = S.toCharArray();
        List<Set> tempList = new ArrayList<Set>();
        //Set<Char> tempSet = new HashSet<Char>();
        t//empSet.add(s[0]);
        int index = 0;
        while (index < s.length) {
            //前面有重复直接加入到原来的set
            Boolean repeatBefore = false;
            for (Set eachSet : tempList) {
                if (eachSet.contains(s[index])) {
                    repeatBefore = true;
                    break;
                }
            }
            if (repeatBefore) {
                index++;
                continue;
            }
            //后面有重复，前面没有重复直接加一个set
            for (int i = index + 1; i < s.length; i++) {
                if ()
            }
            int repeatIndex = checkRepeat(index, s, );
            if(repeatIndex != 0) {
                index = repeatIndex + 1;
            }

            //后面没有重复，前面也没有重复，就new出来一个新的set
            Set<Character> tempSet = new HashSet<Character>();
            tempSet.add(s[index++]);
            tempList.add(tempSet);

        }
    }
    */
}
