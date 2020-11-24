package com.blisskid.fb;
import java.util.*;
public class S0301H {
    LinkedList<Integer> track=new LinkedList();
    LinkedList<Integer> choices=new LinkedList();
    LinkedList<String> res=new LinkedList();

    public List<String> removeInvalidParentheses(String s) {
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
        if(stack.empty()){
            return true;
        }else{
            return false;
        }
    }

    private void f(int dis,String s){
        if(track.size()==dis){
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<s.length();i++){
                if(!track.contains(s.charAt(i))){
                    sb.append(s.charAt(i));
                }
            }
            if(check(sb.toString())){
                res.add(sb.toString());
            }
            return;
        }

        for(int i=0;i<choices.size();i++){
            int index=choices.get(i);
            if(!track.contains(index)){
                track.add(choices.get(i));
            }
            f(dis,s);
            track.removeLast();
        }
    }
}
