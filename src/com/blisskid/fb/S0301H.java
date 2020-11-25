package com.blisskid.fb;
import java.util.*;
public class S0301H {
    public static void main(String[] args) {
        S0301H s=new S0301H();
        System.out.println(s.removeInvalidParentheses(")o(v("));
    }
    LinkedList<Integer> track=new LinkedList();
    LinkedList<Integer> choices=new LinkedList();
    LinkedList<String> res=new LinkedList();

    public List<String> removeInvalidParentheses(String s) {
        StringBuilder sb=new StringBuilder();
        /*
        int index=0;
        char[] sArr=s.toCharArray();
        while(sArr[index]!='('){
            if(sArr[index]!=')'){
                sb.append(sArr[index]);
            }
            index++;
        }

        index=s.length()-1;
        while(sArr[index]!=')'){
            if(sArr[index]!='('){
                sb.append(sArr[index]);
            }
            index--;
        }
        */
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
            for(int i=rightP+1;i<s.length();i++){
                if(s.charAt(i)!='('){
                    sb.append(s.charAt(i));
                }
            }
            s=sb.toString();
        }

        //count '('
        int leftNum=0,rightNum=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                leftNum++;
            }
            if(s.charAt(i)==')'){
                rightNum++;
            }
        }

        int dis=0;
        if(leftNum>rightNum){
            dis=leftNum-rightNum;
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)=='('){
                    choices.add(i);
                }
            }
        }else{
            dis=rightNum-leftNum;
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)==')'){
                    choices.add(i);
                }
            }
        }

        f(dis,s);
        return res;
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

    private void f(int dis,String s){
        if(track.size()==dis){
            StringBuilder sb=new StringBuilder();
            if(dis>0){
                for(int i=0;i<s.length();i++){
                    if(!track.contains(i)){
                        sb.append(s.charAt(i));
                    }
                }
            }else{
                sb.append(s);
            }
            if(check(sb.toString())){
                if(!res.contains(sb.toString())){
                    res.add(sb.toString());
                }
            }
            return;
        }

        for(int i=0;i<choices.size();i++){
            int index=choices.get(i);
            if(!track.contains(index)){
                track.add(choices.get(i));
                f(dis,s);
                track.removeLast();
            }
        }
    }
}
