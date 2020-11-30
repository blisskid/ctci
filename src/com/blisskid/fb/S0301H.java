/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 *
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 * Example 1:
 *
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 * Example 2:
 *
 * Input: "(a)())()"
 * Output: ["(a)()()", "(a())()"]
 * Example 3:
 *
 * Input: ")("
 * Output: [""]
 */

package com.blisskid.fb;
import java.util.*;
public class S0301H {
    public static void main(String[] args) {
        S0301H s=new S0301H();
        System.out.println(s.removeInvalidParentheses("())))"));
    }
    int index=0;
    private List<String> res=new LinkedList();
    public List<String> removeInvalidParentheses(String s) {
        StringBuilder sb=new StringBuilder();
        int leftP=s.indexOf('(');
        int rightP=s.lastIndexOf(')');
        if(leftP!=-1&&rightP!=-1){
            for(int i=0;i<leftP;i++){
                if(s.charAt(i)!=')'){
                    sb.append(s.charAt(i));
                }
            }
            for(int i=leftP;i<=rightP;i++){
                sb.append(s.charAt(i));
            }
            int temp=leftP<rightP?rightP:leftP-1;
            for(int i=temp+1;i<s.length();i++){
                if(s.charAt(i)!='('){
                    sb.append(s.charAt(i));
                }
            }
            s=sb.toString();
        }
        char[] array=s.toCharArray();
        sb=new StringBuilder();
        f(array,sb,0);
        return res;
    }

    private void f(char[] s,StringBuilder track,int index){
        if(index==s.length){
            String str=track.toString();
            if(check(str)){
                if(res.size()==0){
                    res.add(str);
                }else{
                    String first=res.get(0);
                    if(first.length()==str.length()){
                        if(!res.contains(str))
                            res.add(str);
                    }else if(first.length()<str.length()){
                        res.clear();
                        res.add(str);
                    }
                }

            }
            //index=0;
            return;
        }
//        if(index>0&&s[index]!=s[index-1]&&(s[index]=='('||s[index]==')')){
        if(s[index]=='('||s[index]==')'){
            /*
            StringBuilder track1=new StringBuilder(track);
            track1.append(s[index]);
            index++;
            f(s,track1,index);
            track1.deleteCharAt(track1.length()-1);
            StringBuilder track2=new StringBuilder(track);
            f(s,track2,index);
            index--;
            */
            if(s[index]=='('){
                int count=0;
                while(index+1<s.length&&s[index+1]=='('){
                    track.append(s[index]);
                    index++;
                    count++;
                }
                StringBuilder track1=new StringBuilder(track);
                track1.append(s[index]);
                index++;
                count++;
                f(s,track1,index);
                track1.deleteCharAt(track1.length()-1);
                StringBuilder track2=new StringBuilder(track);
                f(s,track2,index);
                index=index-count;
            }else{
                int count=0;
                while(index+1<s.length&&s[index+1]==')'){
                    track.append(s[index]);
                    index++;
                    count++;
                }
                StringBuilder track1=new StringBuilder(track);
                track1.append(s[index]);
                index++;
                count++;
                f(s,track1,index);
                track1.deleteCharAt(track1.length()-1);
                StringBuilder track2=new StringBuilder(track);
                f(s,track2,index);
                index=index-count;
            }
        }else{
            StringBuilder track1=new StringBuilder(track);
            track1.append(s[index]);
            index++;
            f(s,track1,index);
            track1.deleteCharAt(track1.length()-1);
            index--;
        }
    }

    //check if the string is valid
    private boolean check(String s){
        Stack<Character> stack=new Stack();
        boolean fine=false;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==')'){
                fine=false;
                while(!stack.empty()){
                    char c=stack.pop();
                    if(c=='('){
                        fine=true;
                        break;
                    }
                }
                if(stack.empty()&&!fine){
                    return false;
                }
            }else{
                stack.push(s.charAt(i));
            }
        }
        for(int i=0;i<stack.size();i++){
            if(stack.get(i)=='('||stack.get(i)==')'){
                return false;
            }
        }
        return true;
    }
}
