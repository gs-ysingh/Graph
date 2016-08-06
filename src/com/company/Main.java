package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	    AdjacencyGraph graph = new AdjacencyGraph(4, Graph.GraphType.DIRECTED);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        List<Integer> vertices = graph.getAdjacentVertices(0);

        for(Integer i : vertices) {
            System.out.println(i); //1, 2
        }

        List<Integer> dfsList = graph.dfs(2);

        for(Integer i : dfsList) {
            System.out.println(i);
        }



    }
}
