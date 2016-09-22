package com.Prim;

import java.util.*;

/**
 * Created by YSingh on 22/09/16.
 */

class Vertex {
    private String name;
    private List<Edge> edges;

    public Vertex() {
        this.edges = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
}

class Edge implements Comparable<Edge> {
    private Vertex source;
    private Vertex target;
    private double weight;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Vertex getSource() {
        return source;
    }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public Vertex getTarget() {
        return target;
    }

    public void setTarget(Vertex target) {
        this.target = target;
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.getWeight(), o.getWeight());
    }
}

class Prim {
    private Map<String, Vertex> vertices;
    private List<Edge> edges;
    private Queue<Edge> minHeap;
    private List<Edge> mst;

    public Prim() {
        this.vertices = new HashMap<>();
        this.edges = new ArrayList<>();
        this.minHeap = new PriorityQueue<>();
        this.mst = new ArrayList<>();
    }

    public void addEdge(String source, String target, double weight) {
        Vertex v1;
        Vertex v2;

        if(this.vertices.get(source) == null) {
            v1 = new Vertex();
            v1.setName(source);
            this.vertices.put(source, v1);
        }
        else {
            v1 = this.vertices.get(source);
        }

        if(this.vertices.get(target) == null) {
            v2 = new Vertex();
            v2.setName(target);
            this.vertices.put(target, v2);
        }
        else {
            v2 = this.vertices.get(target);
        }

        Edge edge = new Edge();
        edge.setSource(v1);
        edge.setTarget(v2);
        edge.setWeight(weight);

        v1.getEdges().add(edge);

        this.edges.add(edge);
    }

    public void spanning(String vertex) {
        Vertex v = this.vertices.get(vertex);

        this.vertices.remove(vertex);

        while (!this.vertices.isEmpty()) {
            for(Edge e : v.getEdges()) {
                if(this.vertices.get(e.getTarget().getName()) != null) {
                    this.minHeap.add(e);
                }
            }
            Edge edge = this.minHeap.remove();
            this.mst.add(edge);
            v = edge.getTarget();
            this.vertices.remove(v.getName());
        }
    }

    public void mst() {
        double weight = 0;

        for(Edge e : this.mst) {
            System.out.println(e.getSource().getName() + " ->" + e.getTarget().getName());
            weight = weight + e.getWeight();
        }

        System.out.println("Weight: " + weight);
    }

    public static void main(String[] args) {
        Prim graph = new Prim();
        graph.addEdge("0", "1", 10);
        graph.addEdge("0", "2", 6);
        graph.addEdge("0", "3", 5);
        graph.addEdge("1", "3", 15);
        graph.addEdge("2", "3", 4);
        graph.spanning("0");
        graph.mst();
    }
}
