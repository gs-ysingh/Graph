package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by YSingh on 07/08/16.
 */
public class AdjacencyGraph implements Graph {
    private List<Node> vertexList = new ArrayList<>();
    private GraphType graphType = GraphType.DIRECTED;
    int numVertices = 0;

    public AdjacencyGraph(int numVertices, GraphType graphType) {
        this.numVertices = numVertices;
        for (int i = 0; i < this.numVertices; i++) {
            this.vertexList.add(new Node(i));
        }
        this.graphType = graphType;
    }

    @Override
    public void addEdge(int v1, int v2) {
        if(v1 < 0 || v2 < 0 || v1 >= numVertices || v2 >= numVertices) {
            throw new IllegalArgumentException("Vertex number not valid: " + v1 + ", " + v2);
        }

        this.vertexList.get(v1).addEdge(v2);

        if(graphType == GraphType.UNDIRECTED) {
            this.vertexList.get(v2).addEdge(v1);
        }
    }

    @Override
    public List<Integer> getAdjacentVertices(int v) {
        if(v >= this.numVertices || v < 0) {
            throw new IllegalArgumentException("Vertex number not valid: " + v);
        }
        return this.vertexList.get(v).getAdjacentVertices();
    }

    public List<Integer> dfs(int v) {
        if(v >= this.numVertices || v < 0) {
            throw new IllegalArgumentException("Vertex number not valid: " + v);
        }

        List<Integer> dfsList = new ArrayList<>();
        this.dfsUtil(v, dfsList);

        return dfsList;
    }

    public void dfsUtil(int v, List<Integer> dfsList) {
        if(dfsList.size() != this.numVertices) {

            dfsList.add(v);
            List<Integer> list = this.vertexList.get(v).getAdjacentVertices();

            for(Integer vertex : list) {
                if(!dfsList.contains(vertex)) {
                    this.dfsUtil(vertex, dfsList);
                }
            }
        }

        return;
    }
}
