package com.blisskid.fb;
import java.util.*;

public class S0282H {
    public static void main(String[] args) {
        S0282H s=new S0282H();
        System.out.println(s.addOperators("3456237490",9191));
//        System.out.println(s.cal("+@@*@+@-+","3456237490",9191));
    }

    List<String> res = new LinkedList();
    List<String> list = new LinkedList();
    //List<Character> track=new LinkedList();
    //int index=0;
    //List<Character> ops=new LinkedList();
    StringBuilder sb=new StringBuilder();
    char[] operators=new char[]{'+','-','*','@'};

    public List<String> addOperators(String num, int target) {
        if(num.length()==0){
            return res;
        }
        f(num,target);
        return res;
    }

    private void f(String num, int target){
        if(sb.length()==num.length()-1){
            list.add(new String(sb.toString()));

            if(cal(sb.toString(),num,target)){
                res.add(combine(sb.toString(),num));
            }

        }else{
            //sb.append(num.charAt(index));
            //track.add(num.charAt(index));
            //index++;
            for(int j=0;j<4;j++){
                //if(track)
                sb.append(operators[j]);
                //ops.add(operators[j]);
                f(num,target);
                sb.deleteCharAt(sb.length()-1);
            }
            //index--;
            //sb.deleteCharAt(sb.length()-1);
        }
    }

    private class Obj{
        int num;

    }
    private boolean cal(String ops, String num,int target){
        Stack<Character> s=new Stack();
        Stack<Integer> d=new Stack();
        d.push(num.charAt(0)-'0');
        for(int i=0;i<ops.length();i++){
            char c=ops.charAt(i);
            if(c=='@'){
                int last=d.pop();
                if(last==0)
                    return false;
                int next=num.charAt(i+1)-'0';
                d.push(last*10+next);
            }else{
                s.push(c);
                d.push(num.charAt(i+1)-'0');
            }
        }

        Stack<Character> s1=new Stack<>();
        Stack<Integer> d1=new Stack<>();
        s1.addAll(s);
        d1.addAll(d);
        s.removeAllElements();
        d.removeAllElements();
        d.push(d1.get(0));
        for(int i=0;i<s1.size();i++){
            char c=s1.get(i);
            if(c=='*'){
                int last=d.pop();
                int next=d1.get(i+1);
                d.push(last*next);
            }else{
                s.push(c);
                d.push(d1.get(i+1));
            }
        }

        while(d.size()>1){
            int next=d.pop();
            char c=s.pop();
            int last=d.pop();
            if(c=='+'){
                d.push(last+next);
            }else{
                d.push(last-next);
            }
        }
        return d.pop()==target;
    }

    private String combine(String ops, String num){
        StringBuilder sb=new StringBuilder();
        sb.append(num.charAt(0));
        for(int i=0;i<ops.length();i++){
            char c=ops.charAt(i);
            if(c!='@')
                sb.append(ops.charAt(i));
            sb.append(num.charAt(i+1));
        }
        return sb.toString();
    }
}
