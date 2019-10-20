package com.blisskid.basic;


import java.util.Arrays;
import java.util.HashMap;

public class Sort {

    private int count = 0;

    public static void main(String[] args) {
        Sort sortClass = new Sort();
        int[] array = new int[]{1, 22, 41, 7, 10, 54, 3, 8};
        System.out.println(Arrays.toString(array));
        sortClass.quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
        HashMap map = new HashMap();
        Long l = 3232l;
        Float f = 9329329f;


    }

    void bubbleSort(int[] array) {
        
    }

    void mergesort(int[] array) {
        int[] helper = new int[array.length];
        mergesort(array, helper, 0, array.length - 1);
    }

    void mergesort(int[] array, int[] helper, int low, int high) {


        if (low < high) {
            int middle = (low + high) / 2;
            mergesort(array, helper, low, middle);
            mergesort(array, helper, middle + 1, high);
            merge(array, helper, low, middle, high);
        }
    }

    void merge(int[] array, int[] helper, int low, int middle, int high) {


        for (int i = low; i <= high; i++) {
            helper[i] = array[i];
        }

        int helperLeft = low;
        int helperRight = middle + 1;
        int current = low;

        while (helperLeft <= middle && helperRight <= high) {
            if (helper[helperLeft] <= helper[helperRight]) {
                array[current] = helper[helperLeft];
                helperLeft++;
            } else {
                array[current] = helper[helperRight];
                helperRight++;
            }
            current++;
        }

        int remaining = middle - helperLeft;
        for (int i = 0; i <= remaining; i++) {
            array[current + i] = helper[helperLeft + i];
        }

    }

    void quickSort(int[] arr, int left, int right) {
        int index = partition(arr, left, right);
        if (left < index - 1) {
            quickSort(arr, left, index - 1);
        }
        if (index < right) {
            quickSort(arr, index, right);
        }
    }

    int partition(int[] arr, int left, int right) {
        int mid = (left + right) / 2;
        int pivot = arr[mid];
        while (arr[left] < pivot) left++;
        while (arr[right] > pivot) right--;

        if (left <= right) {
            int tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            left++;
            right--;
        }

        return left;
    }
}
