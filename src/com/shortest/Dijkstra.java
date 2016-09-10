package com.shortest;

import java.util.*;

/**
 * Created by YSingh on 11/09/16.
 */

class Vertex implements Comparable<Vertex> {
    private String name;
    private Set<Vertex> adjacencyList;
    private double distance;
    private Vertex predecessor;


    public Set<Edge> getEdges() {
        return edges;
    }

    public void setEdges(Set<Edge> edges) {
        this.edges = edges;
    }

    private Set<Edge> edges;

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    private boolean visited;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Vertex> getAdjacencyList() {
        return adjacencyList;
    }

    public void setAdjacencyList(Set<Vertex> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Vertex getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Vertex predecessor) {
        this.predecessor = predecessor;
    }

    @Override
    public int compareTo(Vertex o) {
        return Double.compare(this.getDistance(), o.getDistance());
    }
}

class Edge {
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

    public Vertex getDestination() {
        return destination;
    }

    public void setDestination(Vertex destination) {
        this.destination = destination;
    }

    public Edge() {

    }

    private double weight;
    private Vertex source;
    private Vertex destination;
}

class Graph {
    private int V;
    private Map<String, Vertex> vertexList;
    private List<Edge> edgeList;

    public Graph(int V) {
        this.V = V;
        vertexList = new HashMap<>();
        edgeList = new ArrayList<>();

        for(Integer i = 0; i < this.V; i++) {
            Vertex v = new Vertex();
            v.setName(i.toString());
            v.setAdjacencyList(new HashSet<>());
            v.setDistance(Double.MAX_VALUE);
            v.setPredecessor(null);
            v.setVisited(false);
            v.setEdges(new HashSet<>());

            vertexList.put(v.getName(), v);
        }
    }

    public void addEdge(Integer source, Integer destination, double weight) {
        Vertex s = this.vertexList.get(source.toString());
        Vertex d = this.vertexList.get(destination.toString());

        s.getAdjacencyList().add(d);

        Edge edge = new Edge();
        edge.setSource(s);
        edge.setDestination(d);
        edge.setWeight(weight);

        s.getEdges().add(edge);

        this.edgeList.add(edge);

    }

    public void showDistance() {
        for(String key : this.vertexList.keySet()) {
            System.out.println(key + ": "  + this.vertexList.get(key).getDistance());
        }
    }

    public void path(Integer source, Integer destination) {
        Vertex d = this.vertexList.get(destination.toString());
        Vertex s = this.vertexList.get(source.toString());

        if (d.getPredecessor().getName() == s.getName()) {
            System.out.println(s.getName());
            System.out.println(d.getName());
            return;
        }

        this.path(source, Integer.parseInt(d.getPredecessor().getName()));
        System.out.println(destination.toString());
    }

    public void dijkstra(Integer source) {
        Vertex s = this.vertexList.get(source.toString());
        s.setDistance(0);

        Queue<Vertex> queue = new PriorityQueue<>();
        queue.add(s);

        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            vertex.setVisited(true);

            for(Edge t : vertex.getEdges()) {
                Vertex u = t.getSource();
                Vertex v = t.getDestination();
                Double weight = t.getWeight();

                if(v.getDistance() > u.getDistance() + weight) {
                    v.setDistance(u.getDistance() + weight);
                    v.setPredecessor(u);
                }

                if(!v.isVisited()) {
                    queue.add(v);
                    v.setVisited(true);
                }
            }
        }

    }
}

public class Dijkstra {

    public static void main(String[] args) {
        //        int V = 9;
        //	      Graph graph = new Graph(9);
        //        graph.addEdge(0, 1, 4);
        //        graph.addEdge(0, 7, 8);
        //        graph.addEdge(1, 2, 8);
        //        graph.addEdge(1, 7, 11);
        //        graph.addEdge(2, 3, 7);
        //        graph.addEdge(2, 8, 2);
        //        graph.addEdge(2, 5, 4);
        //        graph.addEdge(3, 4, 9);
        //        graph.addEdge(3, 5, 14);
        //        graph.addEdge(4, 5, 10);
        //        graph.addEdge(5, 6, 2);
        //        graph.addEdge(6, 7, 1);
        //        graph.addEdge(6, 8, 6);
        //        graph.addEdge(7, 8, 7);
        //
        //        graph.dijkstra(0);
        //        graph.showDistance();
        //        graph.path(0, 8);

        int V = 4;
        Graph graph = new Graph(4);
        graph.addEdge(0,1,5) ;
        graph.addEdge(1,3,1) ;
        graph.addEdge(0,2,2) ;
        graph.addEdge(2,1,1) ;
        graph.addEdge(2,3,7) ;

        graph.dijkstra(0);
        graph.showDistance();
        graph.path(0, 3);

    }
}

