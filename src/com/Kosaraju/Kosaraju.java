package com.Kosaraju;

import java.util.*;

/**
 * Created by YSingh on 23/09/16.
 */

class Vertex {
    private String name;
    private Set<Vertex> adjacencySet;
    private String componentName;
    private boolean visited = false;

    public Vertex(String name, String componentName) {
        this.name = name;
        this.adjacencySet = new HashSet<>();
        this.componentName = componentName;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Vertex> getAdjacencySet() {
        return adjacencySet;
    }

    public void setAdjacencySet(Set<Vertex> adjacencySet) {
        this.adjacencySet = adjacencySet;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }
}

public class Kosaraju {
    Map<String, Vertex> vertexMap;

    public Kosaraju() {
        this.vertexMap = new HashMap<>();
    }

    public void addEdge(String source, String target) {
        Vertex v;
        Vertex t;

        if(this.vertexMap.get(source) == null) {
            v = new Vertex(source, source);
            this.vertexMap.put(source, v);
        }
        else {
            v = this.vertexMap.get(source);
        }

        if(this.vertexMap.get(target) == null) {
            t = new Vertex(target, target);
            this.vertexMap.put(target, t);
        }
        else {
            t = this.vertexMap.get(target);
        }

        v.getAdjacencySet().add(t);
    }

    public void scc() {
        Stack<Vertex> dfsList = new Stack<Vertex>();

        //first dfs
        for(String key : this.vertexMap.keySet()) {
            Vertex v = this.vertexMap.get(key);
            if(!v.isVisited()) {
                dfsUtil(v, dfsList);
            }
        }

        //transpose the graph
        Map<String, Vertex> tGraph = this.transpose();

        //second dfs in order of dfsList
        while(!dfsList.isEmpty()) {
            Vertex v = dfsList.pop();
            Vertex vertex = tGraph.get(v.getName());

            if(!vertex.isVisited()) {
                dfsUtil(vertex, vertex.getName());
            }
        }

        //Making scc map to print in correct order
        Map<String, List<Vertex>> sccMap = new HashMap<>();

        for(String key : tGraph.keySet()) {
            if(sccMap.get(tGraph.get(key).getComponentName()) == null) {
                sccMap.put(tGraph.get(key).getComponentName(), new ArrayList<>());
            }
            sccMap.get(tGraph.get(key).getComponentName()).add(tGraph.get(key));
        }

        for(String key: sccMap.keySet()) {
            for(Vertex v : sccMap.get(key)) {
                System.out.print(v.getName() + " ");
            }
            System.out.println();
        }
    }

    public void dfsUtil(Vertex v, Stack<Vertex> dfsList) {
        v.setVisited(true);

        for(Vertex vertex : v.getAdjacencySet()) {
            if(!vertex.isVisited()) {
                dfsUtil(vertex, dfsList);
            }
        }

        dfsList.push(v);
    }

    public void dfsUtil(Vertex v, String componentName) {
        v.setVisited(true);
        v.setComponentName(componentName);

        for(Vertex vertex : v.getAdjacencySet()) {
            if(!vertex.isVisited()) {
                dfsUtil(vertex, componentName);
            }
        }
    }

    public Map<String, Vertex> transpose() {
        Map<String, Vertex> tGraph = new HashMap<>();

        for(String key : this.vertexMap.keySet()) {
            tGraph.put(key, new Vertex(key, key));
        }

        for(String key : this.vertexMap.keySet()) {
            Vertex vertex = this.vertexMap.get(key);

            for(Vertex v : vertex.getAdjacencySet()) {
                tGraph.get(v.getName()).getAdjacencySet().add(tGraph.get(vertex.getName()));
            }
        }

        return tGraph;
    }

    public static void main(String[] args) {
        Kosaraju g = new Kosaraju();

        g.addEdge("1", "0");
        g.addEdge("0", "2");
        g.addEdge("2", "1");
        g.addEdge("0", "3");
        g.addEdge("3", "4");
        g.scc();
    }
}
