package com.blisskid.basic;

public class LoopMethods {
    public static void main(String[] args) {
        LoopMethods lm=new LoopMethods();
        /**
         *  1   2   3   4
         *  5   6   7   8
         *  9   10  11  12
         *  13  14  15  16
         */
        int[][] array=new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        lm.oblique(array);
    }

    private void oblique(int[][] arr){
        int n=arr.length;
        for (int i = 0; i < n; i++) {
            int j=0,k=i+j;
            for (; j < n&&k<n; j++,k=i+j) {
                System.out.print(arr[j][k]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
