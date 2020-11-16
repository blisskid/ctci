package com.blisskid.fb;
import java.util.*;

public class S0273H {

    public static void main(String[] args) {
        System.out.println(new S0273H().numberToWords(1000));
    }
    //calculate the lenght of num
    public String numberToWords(int num) {
        if(num==0){
            return "Zero";
        }
        String[] arr1=new String[]{"","One","Two","Three","Four","Five","Six","Seven","Eight","Nine"};
        String[] arr2=new String[]{"Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
        String[] arr3=new String[]{"","Ten","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
        String numStr=new Integer(num).toString();
        char[] numArray=numStr.toCharArray();
        int[] numsArr=new int[numArray.length];
        for(int i=numArray.length-1;i>=0;i--){
            numsArr[i]=numArray[i]-'0';
        }
        Stack<String> stack=new Stack();

        for(int i=numsArr.length-1;i>=0;i--){
            int dis=numsArr.length-i;

            if(dis==1){
                if(i-1>=0&&numsArr[i-1]==1){
                    stack.push(arr2[numsArr[i]]);
                    i--;
                }else{
                    stack.push(arr1[numsArr[i]]);
                }
            }else if(dis==2||dis==5||dis==8){
                stack.push(arr3[numsArr[i]]);
            }else if(dis==3||dis==6||dis==9){
                stack.push("Hundred");
                stack.push(arr1[numsArr[i]]);
            }else if(dis==4){
                stack.push("Thousand");
                if(i-1>=0&&numsArr[i-1]==1){
                    stack.push(arr2[numsArr[i]]);
                    i--;
                }else{
                    stack.push(arr1[numsArr[i]]);
                }
            }else if(dis==7){
                stack.push("Million");
                if(i-1>=0&&numsArr[i-1]==1){
                    stack.push(arr2[numsArr[i]]);
                    i--;
                }else{
                    stack.push(arr1[numsArr[i]]);
                }
            }else if(dis==10){
                stack.push("Billion");
                if(i-1>=0&&numsArr[i-1]==1){
                    stack.push(arr2[numsArr[i]]);
                    i--;
                }else{
                    stack.push(arr1[numsArr[i]]);
                }
            }

        }
        if(stack.isEmpty()){
            return "";
        }

        StringBuilder sb = new StringBuilder(stack.pop());
        while(!stack.isEmpty()){
            if(stack.peek().equals("")){
                stack.pop();
                continue;
            }
            sb.append(" ");
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
