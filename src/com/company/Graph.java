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
