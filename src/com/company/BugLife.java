package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by YSingh on 09/08/16.
 */
public class BugLife {
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Boolean> list = new ArrayList<>();

        while (n > 0) {
            int vertex = in.nextInt();
            int edges = in.nextInt();

            AdjacencyGraph graph = new AdjacencyGraph(vertex, Graph.GraphType.UNDIRECTED);

            while (edges > 0) {
                int u = in.nextInt();
                int v = in.nextInt();
                graph.addEdge(u - 1, v - 1);
                edges--;
            }
            list.add(graph.isCycle(0));
            n--;
        }

        int i = 1;
        for(boolean b: list) {
            System.out.println("Scenario #" + i + ":");
            if(b) {
                System.out.println("Suspicious bugs found!");
            } else {
                System.out.println("No suspicious bugs found!");
            }
            i++;
        }
    }
}
