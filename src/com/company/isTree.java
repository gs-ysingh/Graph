package com.company;

import java.util.Scanner;

//SPOJ Problem PT07Y

/**
 * Created by YSingh on 08/08/16.
 */
public class isTree {
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        AdjacencyGraph graph = new AdjacencyGraph(n, Graph.GraphType.UNDIRECTED);

        while (m > 0) {
            int u = in.nextInt();
            int v = in.nextInt();

            graph.addEdge(u - 1, v - 1);
            m --;
        }

        boolean isCycle = graph.isCycle(0);

        if(isCycle) {
            System.out.println("NO");
        }
        else {
            System.out.println("YES");
        }

    }
}
