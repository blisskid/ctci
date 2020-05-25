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

    public List<Integer> partitionLabels(String S) {
        List<Integer> result = new ArrayList<>();

        int[] letters = new int[26];
        for (int i = 0; i < S.length(); i++) {
            letters[S.charAt(i) - 'a'] = i;
        }

        int start = 0, end = 0, index = 0;
        while (index < S.length()) {
            if (letters[S.charAt(index) - 'a'] > end) {
                end = letters[S.charAt(index) - 'a'];
            }
            if (index == end) {
                result.add(end - start + 1);
                index++;
                start = index;
                continue;
            }
            index++;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new S0763M().partitionLabels("ababcbacadefegdehijhklij"));
    }

    private class LetterObj implements Comparable<LetterObj> {
        public int startIndex;
        public int endIndex;
        public Integer length;
        public Boolean removed = false;
        public void calLength() {
            length = endIndex - startIndex + 1;
        }

        LetterObj (int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.calLength();
        }

        LetterObj () {}

        @Override
        public int compareTo(LetterObj o) {
            if (o.length > length) {
                return 1;
            } else if (o.length == length)
                return 0;
            else
                return -1;
        }
    }

    public List<Integer> partitionLabels2(String S) {
        char[] s = S.toCharArray();
        Map<Character, LetterObj> letters = new LinkedHashMap<>();
        //LetterObj[] letters = new LetterObj[26];
        //find the distinct letter
        for (int i = 0; i < s.length; i++) {
            if (letters.get(s[i]) == null) {
                letters.put(s[i], new LetterObj(i, i));
            } else {
                if (i < letters.get(s[i]).startIndex) {
                    letters.get(s[i]).startIndex = i;
                    letters.get(s[i]).calLength();
                } else if (i > letters.get(s[i]).endIndex) {
                    letters.get(s[i]).endIndex = i;
                    letters.get(s[i]).calLength();
                }
            }
        }
        //Arrays.sort(letters);
        for (Map.Entry<Character, LetterObj> ele : letters.entrySet()) {
            if (!ele.getValue().removed) {
                for (Map.Entry<Character, LetterObj> ele1 : letters.entrySet()) {
                    if (ele1.getKey() != ele.getKey() && !ele1.getValue().removed) {
                        if (ele1.getValue().startIndex < ele.getValue().startIndex) {
                            if (ele1.getValue().endIndex >= ele.getValue().startIndex && ele1.getValue().endIndex < ele.getValue().endIndex) {
                                ele.getValue().startIndex = ele1.getValue().startIndex;
                                ele.getValue().calLength();
                                ele1.getValue().removed = true;
                                //letters.remove(s[j]);
                            } else if (ele1.getValue().endIndex >= ele.getValue().endIndex) {
                                ele.getValue().startIndex = ele1.getValue().startIndex;
                                ele.getValue().endIndex = ele1.getValue().endIndex;
                                ele.getValue().calLength();
                                ele1.getValue().removed = true;
                                //letters.remove(s[j]);
                            }
                        } else if (ele1.getValue().startIndex >= ele.getValue().startIndex && ele1.getValue().startIndex <= ele.getValue().endIndex) {
                            if (ele1.getValue().endIndex <= ele.getValue().endIndex) {
                                ele1.getValue().removed = true;
                                //letters.remove(s[j]);
                            } else {
                                ele.getValue().endIndex = ele1.getValue().endIndex;
                                ele.getValue().calLength();
                                ele1.getValue().removed = true;
                                //letters.remove(s[j]);
                            }
                        }
                    }
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Character, LetterObj> letter : letters.entrySet()) {
            if (!letter.getValue().removed)
                result.add(letter.getValue().length);
        }

        return result;
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
