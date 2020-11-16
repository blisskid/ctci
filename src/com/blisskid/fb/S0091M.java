package com.blisskid.fb;

public class S0091M {

    public static void main(String[] args) {
        System.out.println(new S0091M().numDecodings("2101"));
    }

    private String trimStartZero(String s){
        StringBuilder sb=new StringBuilder();
        int i=0;
        while(i<s.length()&&s.charAt(i)=='0'){
            i++;
        }
        while(i<s.length()){
            sb.append(s.charAt(i));
            i++;
        }
        return sb.toString();
    }

    private String trimOneZero(String s){
        return s.replace("10","");
    }

    private boolean containsZero(String s){
        s=s.replace("10","");
        s=s.replace("20","");
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='0'){
                return true;
            }
        }
        return false;
    }

    public int numDecodings(String s) {
        if(s==null)
            return 0;
        if(s.equals("0"))
            return 0;

        String temp=trimStartZero(s);
        if(temp.equals(""))
            return 0;
        //temp=trimOneZero(temp);
        if(containsZero(temp)){
            return 0;
        }
        char[] cArr=temp.toCharArray();
        int start=0;
        int end=1;
        int count=0;
        while(start<cArr.length&&end<cArr.length){
            if(f(cArr,start,end)){
                count++;
            }
            start++;
            end++;
        }
        return count+1;
    }

    private boolean f(char[] arr,int start,int end){
        StringBuilder sb=new StringBuilder();
        sb.append(arr[start]);
        sb.append(arr[end]);
        Integer val=Integer.parseInt(sb.toString());
        if(val>10&&val<20||val>20&&val<=26){
            return true;
        }else{
            return false;
        }
    }
/*
    private int f2(char[] arr,int start,int end){
        int size=end-start+1;
        if(size==1)
            return 1;
        if(size==2){
            return f1(arr,start,end);
        }
        int mid=(start+end) / 2;
        int left=f(arr,start,mid);
        int right=f(arr,mid+1,end);
        return left+right+f1(arr,mid,mid+1);
    }

    private int f1(char[] arr,int start,int end){
        StringBuilder sb=new StringBuilder();
        sb.append(arr[start]);
        sb.append(arr[end]);
        Integer val=Integer.parseInt(sb.toString());
        if(val==0){
            return 0;
        }else if(val>0&&val<10){
            return 1;
        }else if(val>=10&&val<=26){
            return 2;
        }else{
            return 1;
        }
    }
    */

}
