package com.Counting;

import java.util.Arrays;

/**
 * Created by YSingh on 01/10/16.
 */
public class CountingSort {
    public static void main(String[] args) {
        int [] numbers = { 2, 1, 3, 4, 2, 1, 6, 9, 4, 6, 9, 3 };
        int range = 9;

        CountingSort obj = new CountingSort();
        int [] sortedValues = obj.countingSort(numbers, range);

        for(int i = 0; i < sortedValues.length; i++) {
            System.out.println(sortedValues[i]);
        }
    }

    private int [] countingSort(int[] numbers, int range) {
        int [] copyNumbers = new int[numbers.length];
        int [] M = new int[range + 1];
        Arrays.fill(M, 0);

        //step 1
        for(int i = 0; i < numbers.length; i++) {
            M[numbers[i]] = M[numbers[i]] + 1;
        }

        //step 2
        for(int i = 1; i < M.length; i++ ) {
            M[i] = M[i] + M[i - 1];
        }

        Arrays.fill(copyNumbers, 0);

        //step 3
        for(int i = 0; i < numbers.length; i++) {
            copyNumbers[M[numbers[i]] - 1] = numbers[i];
            M[numbers[i]] = M[numbers[i]] - 1;
        }

        return copyNumbers;
    }
}
