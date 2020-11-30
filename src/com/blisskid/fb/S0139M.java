package com.blisskid.fb;
import java.util.*;

public class S0139M {
    public static void main(String[] args) {
        LinkedList<String> choices=new LinkedList();
        choices.add("bc");
        choices.add("ca");
//        choices.add("1");
//        choices.add("2");
        //choices.add("3");
        System.out.println(new S0139M().wordBreak("cbca",choices));

        choices.add("cc");
        choices.add("ac");
//        choices.add("1");
//        choices.add("2");
        //choices.add("3");
        System.out.println(new S0139M().wordBreak("ccaccc",choices));
    }

    LinkedList<String> track=new LinkedList();
    LinkedList<String> choices=new LinkedList();
    LinkedList<String> res=null;
    String s=new String();

    public boolean wordBreak(String s, List<String> wordDict) {
        this.track=new LinkedList();
        this.choices=new LinkedList();
        choices.addAll(wordDict);
        this.s=new String(s);
        f1();
//        return f();
        return res!=null;
    }

    private void f1(){
        if(choices.isEmpty()){
            if(check()){
                res=new LinkedList<String>(track);
            }
            System.out.println(track);
            return;
        }
        //List<String> l=new ArrayList();
        //l.addAll(choices);
        String str=choices.removeFirst();
        track.add(str);
        f1();
        track.removeLast();
        f1();
        choices.addFirst(str);
    }

    private boolean check(){
        String s=new String(this.s);
        for(int i=0;i<track.size();i++){
            String str=track.get(i);
            if(s.contains(str)){
                //delete str
                s=s.replaceAll(str,"");
            }
        }
        return s.isEmpty();
    }
}
