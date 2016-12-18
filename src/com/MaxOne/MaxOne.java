package com.MaxOne;

import java.util.*;

class TestClass {

    int maxValue(int mat[][], int R, int C)
    {
        int [][] hist = new int [R+1][C+1];
        for (int i=0; i<C; i++)
        {
            hist[0][i] = mat[0][i];
            for (int j=1; j<R; j++)
                hist[j][i] = (mat[j][i]==0)? 0: hist[j-1][i]+1;
        }
        for (int i=0; i<R; i++)
        {
            int [] count = new int[R + 1];
            Arrays.fill(count, 0);

            for (int j=0; j<C; j++)
                count[hist[i][j]]++;

            int col_no = 0;
            for (int j=R; j>=0; j--)
            {
                if (count[j] > 0)
                {
                    for (int k=0; k<count[j]; k++)
                    {
                        hist[i][col_no] = j;
                        col_no++;
                    }
                }
            }
        }

        int curr_area, max_area = 0;
        for (int i=0; i<R; i++)
        {
            for (int j=0; j<C; j++)
            {
                curr_area = (j+1)*hist[i][j];
                if (curr_area > max_area)
                    max_area = curr_area;
            }
        }
        return max_area;
    }

    int maxHist(int row[], int col)
    {
        Stack<Integer> result = new Stack();

        int top_val;

        int max_area = 0;

        int area = 0;
        int i = 0;

        while (i < col)
        {
            if (result.empty() || row[result.peek()] <= row[i])
                result.push(i++);

            else
            {
                top_val = row[result.peek()];
                result.pop();
                area = top_val * i;

                if (!result.empty())
                    area = top_val * (i - result.peek() - 1 );
                max_area = Math.max(area, max_area);
            }
        }
        while (!result.empty())
        {
            top_val = row[result.peek()];
            result.pop();
            area = top_val * i;
            if (!result.empty())
                area = top_val * (i - result.peek() - 1 );

            max_area = Math.max(area, max_area);
        }
        return max_area;
    }

    int maxRectangle(int [][] M, int row, int col)
    {

        int result = maxHist(M[0], col);
        for (int i = 1; i < row; i++)
        {

            for (int j = 0; j < col; j++)
                if (M[i][j] == 1) {
                    M[i][j] += M[i - 1][j];
                }
            result = Math.max(result, maxHist(M[i], col));
        }
        return result;
    }

    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        while (n > 0) {
            int R = in.nextInt();
            int C = in.nextInt();
            int r = 0;
            int[][] M = new int[R][C];
            while (r < R) {
                int c = 0;
                while (c < C) {
                    int value = in.nextInt();
                    M[r][c] = value;
                   c++;
               }
               r++;
            }
            TestClass t = new TestClass();

            int max = t.maxValue(M, R, C);
            int min = t.maxRectangle(M, R, C);
            if(max > min) {
                System.out.println("1 " + max);
            }
            else {
                System.out.println("0 " + max);
            }

            n--;
        }

    }
}