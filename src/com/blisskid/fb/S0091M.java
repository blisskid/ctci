package com.blisskid.fb;

public class S0091M {

    public static void main(String[] args) {
        System.out.println(new S0091M().numDecodings("226"));
    }

    public int numDecodings(String s) {
        char[] arr=s.toCharArray();
        int len=arr.length;
        if(len==0){
            return 0;
        }
        int[] f=new int[len];
        if(arr[0]=='0')
            f[0]=0;
        else
            f[0]=1;

        for(int i=1;i<len;i++){
            if(arr[i]=='0'&&(arr[i-1]!='1'&&arr[i-1]!='2')){
                return 0;
            }else if(f2(arr,i-1,i)){
                if(i==1){
                    f[1]=2;
                }else{
                    f[i]=f[i-2]+f[i-1];
                }
            }else if(arr[i]=='0'&&(arr[i-1]=='1'||arr[i-1]=='2')){
                if(i==1){
                    f[1]=1;
                }else{
                    f[i]=f[i-2];
                }
            }else{
                f[i]=f[i-1];
            }
        }
        return f[len-1];
    }

    private boolean f2(char[] arr,int start,int end){
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
}
