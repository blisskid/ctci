package com.blisskid.leetcode.heap;

import java.util.Arrays;

public class S1046 {

    public static void main(String[] args) {
        S1046 s = new S1046();
        int[] stones = {434,667,378,919,212,902,240,257,208,996,411,222,557,634,425,949,755,833,785,886,40,159,932,157,764,916,85,300,130,278};
        System.out.println(s.lastStoneWeight(stones));
    }

    public int lastStoneWeight(int[] stones) {
        int[] stones1 = {434,667,378,919,212,902,240,257,208,996,411,222,557,634,425,949,755,833,785,886,40,159,932,157,764,916,85,300,130,278};
        Arrays.sort(stones1);
        Heap heap = new Heap(stones.length);
        for (int i = 0; i < stones.length; i++) {
            heap.insert(stones[i]);
        }
        while (heap.getSize() > 1) {
            int firstStone = heap.remove();
            int secondStone = heap.remove();
            int result = Math.abs(firstStone - secondStone);
            if (result != 0) {
                heap.insert(result);
            }
        }

        if (heap.getSize() == 0) return 0;
        return heap.remove();
    }

    //this is maxHeap
}
