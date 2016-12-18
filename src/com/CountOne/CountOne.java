package com.CountOne;

import java.util.Arrays;

/**
 * Created by YSingh on 02/10/16.
 */
public class CountOne {
    public static void main(String[] args) {
        CountOne obj = new CountOne();
        int [][] mat = {
                            {0, 1, 0, 1, 0},
                            {0, 1, 1, 1, 1},
                            {1, 1, 1, 0, 1},
                            {1, 1, 1, 1, 1},
                            {0, 0, 0, 0, 0},
                        };

        System.out.println(obj.maxRectangle(mat, 5, 5));
    }

    //non-increasing counting sort order
    private int [] countingSort(int[] numbers, int range) {
        int [] copyNumbers = new int[numbers.length];
        int [] M = new int[range + 1];
        Arrays.fill(M, 0);
        int len = numbers.length;

        //step 1
        for(int i = 0; i < len; i++) {
            M[numbers[i]] = M[numbers[i]] + 1;
        }

        //step 2
        for(int i = 1; i < M.length; i++ ) {
            M[i] = M[i] + M[i - 1];
        }

        Arrays.fill(copyNumbers, 0);

        //step 3
        for(int i = 0; i < len; i++) {
            copyNumbers[len - M[numbers[i]]] = numbers[i];
            M[numbers[i]] = M[numbers[i]] - 1;
        }

        return copyNumbers;
    }

    private int maxRectangle(int[][] mat, int row, int col) {
        int [][] histogram = new int[row][col];

        //step 1: create a histogram to count the consecutive one's
        for(int i = 0; i < row; i++) {
            if(i == 0) {
                histogram[i] = mat[i];
            }
            else {
                for(int j = 0; j < col; j++) {
                    histogram[i][j] = mat[i][j] == 0 ? mat[i][j] : histogram[i - 1][j] + 1;
                }
            }
        }

        //step 2:
        int range = row;

        for(int i = 0; i < row; i++) {
            histogram[i] = this.countingSort(histogram[i], range);
        }

        //step 3:
        int max_area = Integer.MIN_VALUE;
        int area;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                area = histogram[i][j] * (j + 1);
                if(area > max_area) {
                    max_area = area;
                }
            }
        }

        return max_area;
    }
}
