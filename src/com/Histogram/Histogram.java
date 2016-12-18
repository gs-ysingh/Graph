package com.Histogram;

import java.util.Stack;

/**
 * Created by YSingh on 01/10/16.
 */
public class Histogram {
    public static void main(String[] args) {
        int [] M = {6, 2, 5, 4, 5, 1, 6};
        Histogram obj = new Histogram();
        System.out.println(obj.getMaxArea(M));
    }

    private int getMaxArea(int[] M) {
        int len = M.length;
        Stack<Integer> stack = new Stack<>();

        int max_area = Integer.MIN_VALUE;

        for(int i = 0; i < len; i++) {
            if(!stack.isEmpty() && M[i] < stack.peek()) {
                max_area = this.getStackMaxArea(stack, max_area);
                stack.push(M[i]);
            }
            stack.push(M[i]);
        }

        return this.getStackMaxArea(stack, max_area);
    }

    private int getStackMaxArea(Stack<Integer> stack, int max_area) {
        int count = 1;
        while (!stack.isEmpty()) {
            int value = stack.pop();
            int area = value * count;
            if(area > max_area) {
                max_area = area;
            }
            count++;
        }
        return max_area;
    }
}
