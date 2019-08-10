package com.blisskid;

import java.util.Arrays;

public class Primes {

    boolean[] sieveOfEratorsthenes(int max) {
        boolean[] flags = new boolean[max + 1];

        init(flags);
//        for (int i = 0; i < flags.length; i++) {
//            flags[i] = true;
//        }

        int prime = 2;

        while (prime <= Math.sqrt(max)) {
            crossoff(flags, prime);
            prime = getNextPrime(flags, prime);
        }

        return flags;
    }

    void init(boolean[] flags) {
        for (int i = 0; i < flags.length; i++) {
            flags[i] = true;
        }
    }

    void crossoff(boolean[] flags, int prime) {
        for (int i = prime * prime; i < flags.length; i += prime) {
            flags[i] = false;
        }
    }

    int getNextPrime(boolean[] flags, int prime) {
        int next = prime + 1;
        while (next < flags.length && !flags[next]) {
            next++;
        }
        return next;
    }


    public static void main(String[] args) {
        // write your code here
        Primes primes = new Primes();
        System.out.println(Arrays.toString(primes.sieveOfEratorsthenes(8)));
    }

}
