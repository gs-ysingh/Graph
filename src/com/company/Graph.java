package com.company;

import java.util.List;

/**
 * Created by YSingh on 06/08/16.
 */
public interface Graph {

    enum GraphType {
        DIRECTED,
        UNDIRECTED
    }

    public void addEdge(int v1, int v2);

    List<Integer> getAdjacentVertices(int v);

}
//
//        DFS/BFS
//        Shortest path algorithms (Dijkstra’s algorithm, Floyd’s algorithm, Bellman--Ford, Johnson’s algorithm)
//        MST (Prim’s algorithm, Kruskal’s algorithm; you don’t necessarily need to know both)
//        A max flow algorithm (Edmonds–Karp algorithm, or a preflow-push method; the latter tends to be faster in the worst case; for ICPC you’ll want Dinić’s algorithm in your codebook since it’s faster still) and the application to min cut and bipartite matching, and, through König’s theorem, to minimum vertex cover in a bipartite graph
//        A min-cost max flow algorithm
//        Eulerian path (Hierholzer’s algorithm)
//        A strongly connected components algorithm
//        Topological sort
//        Tarjan’s algorithms for finding bridges and articulation points
//        The heavy-light decomposition for trees
