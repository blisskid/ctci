package com.blisskid.fb;

import java.util.*;

public class S0140H {
    public static void main(String[] args) {
        LinkedList<String> choices=new LinkedList();
        choices.add("cat");
        choices.add("cats");
        choices.add("and");
        choices.add("sand");
        choices.add("dog");
        S0140H obj=new S0140H();
        System.out.println(obj.wordBreak("catsanddog",choices));
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set=new HashSet();
        for(String e:wordDict){
            set.add(e);
        }
        StringBuilder[] f=new StringBuilder[s.length()+1];
        //f[0]=new ArrayList();
        //f[0].add(new String(""));
        for(int i=1;i<=s.length();i++){
            f[i]=new StringBuilder();
            for(int j=i-1;j>=0;j--){
                String substr=s.substring(j,i);
                if(set.contains(substr)){
                    if(j==0){
                        f[i].append(substr);
                        f[i].append(",");
                    }else if(f[j].length()>0){
                        String[] strarr=f[j].toString().split(",");
                        for(String str:strarr){
                            if(str.length()!=0){
                                f[i].append(str);
                                f[i].append(" ");
                                f[i].append(substr);
                            }
                        }
                        f[i].append(",");
                    }
                }
            }
        }
        List<String> res=new ArrayList();
        String[] strarr=f[s.length()].toString().split(",");
        for(String str:strarr){
            if(str.length()!=0){
                res.add(str);
            }
        }
        return res;
    }
}
