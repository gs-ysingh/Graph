package com.company;

import java.util.*;

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

    public boolean isCycle(int v) {
        if(v >= this.numVertices || v < 0) {
            throw new IllegalArgumentException("Vertex number not valid: " + v);
        }

        List<Integer> dfsList = new ArrayList<>();
        return this.isCycleUtil(v, dfsList, -1);
    }

    public boolean isCycleUtil(int v, List<Integer> dfsList, int parent) {
        dfsList.add(v);
        List<Integer> list = this.vertexList.get(v).getAdjacentVertices();
        for(Integer vertex : list) {
            if(!dfsList.contains(vertex)) {
                return this.isCycleUtil(vertex, dfsList, v);
            }
            else if(parent != vertex) {
                return true;
            }
        }
        return false;
    }

    public int longestPath(int v) {
        if(v >= this.numVertices || v < 0) {
            throw new IllegalArgumentException("Vertex number not valid: " + v);
        }
        List<Integer> dfsList = new ArrayList<>();

        return this.longestPathUtil(v, dfsList, 0);
    }

    public int longestPathUtil(int v, List<Integer> dfsList, int max) {
        dfsList.add(v);
        List<Integer> list = this.vertexList.get(v).getAdjacentVertices();
        int temp = max;
        for(Integer vertex : list) {
            if(!dfsList.contains(vertex)) {
                int len = 1 + this.longestPathUtil(vertex, dfsList, temp);
                if(len > max) {
                    max = len;
                }
            }
        }
        return max;
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
        dfsList.add(v);
        List<Integer> list = this.vertexList.get(v).getAdjacentVertices();
        for(Integer vertex : list) {
            if(!dfsList.contains(vertex)) {
                this.dfsUtil(vertex, dfsList);
            }
        }
    }

    public void dfsUtilDisconnected(int v, List<Integer> dfsList) {
        if(!dfsList.contains(v)) {
            dfsList.add(v);
        }
        List<Integer> list = this.vertexList.get(v).getAdjacentVertices();

        for(Integer vertex : list) {
            if(!dfsList.contains(vertex)) {
                this.dfsUtilDisconnected(vertex, dfsList);
            }
        }
    }

    public List<Integer> dfsDisconnected() {
        List<Integer> dfsList = new ArrayList<>();

        for(int i = 0; i < this.numVertices; i++) {
            this.dfsUtilDisconnected(i, dfsList);
        }

        return dfsList;
    }

    public List<Integer> bfs(int v) {
        if(v >= this.numVertices || v < 0) {
            throw new IllegalArgumentException("Invalid vertex: " + v);
        }

        List<Integer> bfsList = new ArrayList<>();

        Queue<Integer> queue = new PriorityQueue<>();

        queue.add(v);

        while (!queue.isEmpty()) {
            int vertex = queue.remove();
            bfsList.add(vertex);

            List<Integer> vertices = this.vertexList.get(vertex).getAdjacentVertices();

            for(Integer i : vertices) {
                if(!bfsList.contains(i)) {
                    queue.add(i);
                }
            }
        }

        return bfsList;
    }
}
