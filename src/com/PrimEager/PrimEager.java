package com.PrimEager;

import java.util.*;

/**
 * Created by YSingh on 23/09/16.
 */

class Vertex implements Comparable<Vertex> {
    private String name;
    private List<Edge> edges;
    private double distance = Double.MAX_VALUE;
    private Edge minEdge;

    public Edge getMinEdge() {
        return minEdge;
    }

    public void setMinEdge(Edge minEdge) {
        this.minEdge = minEdge;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

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

    @Override
    public int compareTo(Vertex o) {
        return Double.compare(this.getDistance(), o.getDistance());
    }
}

class Edge implements Comparable<Edge> {
    private Vertex source;
    private Vertex target;
    private double weight;

    public double getWeight() {
        return weight;
    }

    public void setDistance(double weight) {
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

class PrimEager {
    private Map<String, Vertex> vertices;
    private List<Edge> edges;
    private Queue<Vertex> minHeap;
    private List<Vertex> mst;

    public PrimEager() {
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
        edge.setDistance(weight);

        v1.getEdges().add(edge);

        Edge edge2 = new Edge();
        edge2.setSource(v2);
        edge2.setTarget(v1);
        edge2.setDistance(weight);

        v2.getEdges().add(edge2);

        this.edges.add(edge);
        this.edges.add(edge2);
    }

    public void spanning(String vertex) {
        Vertex v = this.vertices.get(vertex);
        v.setDistance(0);
        this.mst.add(v);
        
        this.vertices.remove(vertex);
        boolean flag = false;

        while (!this.vertices.isEmpty()) {
            for(Edge e : v.getEdges()) {
                if(this.vertices.get(e.getTarget().getName()) != null) {
                    if(e.getWeight() < e.getTarget().getDistance()) {
                        e.getTarget().setDistance(e.getWeight());
                        v.setMinEdge(e);
                        if(this.minHeap.contains(e.getTarget())) {
                            this.minHeap.remove(e.getTarget());
                        }
                        this.minHeap.add(e.getTarget());
                        flag = true;
                    }
                }
            }
            v = this.minHeap.poll();
            if(v.getMinEdge() == null && !flag) {
                Queue<Edge> edges = new PriorityQueue<>();
                for(Edge e : v.getEdges()) {
                    edges.add(e);
                }
                Edge e = edges.poll();
                v.setMinEdge(e);
            }
            this.vertices.remove(v.getName());
            this.mst.add(v);
            flag = false;
        }
    }

    public void mst() {
        for(Vertex v : this.mst) {
            if(v.getMinEdge() != null) {
                Edge e = v.getMinEdge();
                System.out.println(v.getName() + " ->" + e.getTarget().getName());
            }
        }
    }

    public static void main(String[] args) {
        PrimEager graph = new PrimEager();
        graph.addEdge("0", "1", 10);
        graph.addEdge("0", "2", 6);
        graph.addEdge("0", "3", 5);
        graph.addEdge("1", "3", 15);
        graph.addEdge("2", "3", 4);
        graph.spanning("0");
        graph.mst();
    }
}


