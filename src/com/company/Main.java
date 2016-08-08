package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	    AdjacencyGraph graph = new AdjacencyGraph(8, Graph.GraphType.UNDIRECTED);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);
        graph.addEdge(6, 7);

        System.out.println("Adjacent vertex of 0: ");

        List<Integer> vertices = graph.getAdjacentVertices(0);

        for(Integer i : vertices) {
            System.out.println(i); //1, 2
        }

        List<Integer> dfsList = graph.dfs(2);

        System.out.println("DFS starting from 2: ");

        for(Integer i : dfsList) {
            System.out.println(i);
        }

        dfsList = graph.dfsDisconnected();

        System.out.println("DFS for disconneted graph: ");

        for(Integer i : dfsList) {
            System.out.println(i);
        }

        List<Integer> bfsList = graph.bfs(2);

        System.out.println("bfs for conneted graph: ");

        for(Integer i : bfsList) {
            System.out.println(i);
        }

    }
}
