package com.Basics;

import java.util.Scanner;

/**
 * Created by YSingh on 09/08/16.
 */
public class LongestPath {
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        AdjacencyGraph graph = new AdjacencyGraph(n, Graph.GraphType.UNDIRECTED);

        while (n > 1) {
            int u = in.nextInt();
            int v = in.nextInt();

            graph.addEdge(u - 1, v - 1);
            n --;
        }

        System.out.println(graph.longestPath(0));

    }
}

//13
//1 2
//1 3
//1 4
//3 5
//3 6
//3 7
//6 8
//6 9
//7 10
//7 11
//10 12
//10 13