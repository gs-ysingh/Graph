package com.union;

import java.util.*;

/**
 * Created by YSingh on 13/09/16.
 */


class Node {
    private String name;
    private int rank;
    private Node parent;

    public Node() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}

class Vertex {
    private String name;
    private Node node;

    public Vertex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}

class Edge implements Comparable<Edge> {
    private double weight;
    private Vertex source;
    private Vertex destination;

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

    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.weight, o.weight);
    }
}

class DisjointSet {
    Map<String, Node> rootNodes;

    public DisjointSet(Map<String, Vertex> vertices) {
        this.rootNodes = new HashMap<>();

        for(String key : vertices.keySet()) {
            Vertex v = vertices.get(key);
            Node n = new Node();
            n.setName(v.getName());
            n.setParent(null);
            n.setRank(0);
            v.setNode(n);
            this.rootNodes.put(v.getName(), n);
        }
    }

    public void union(Node n1, Node n2) {
        String fn1 = this.find(n1);
        String fn2 = this.find(n2);

        if(fn1 == fn2) {
            return;
        }

        Node root1 = this.rootNodes.get(fn1);
        Node root2 = this.rootNodes.get(fn2);

        if(root1.getRank() == root2.getRank()) {
            root1.setParent(root2);
            root2.setRank(root2.getRank() + 1);
        }
        else if(root1.getRank() > root2.getRank()) {
            root2.setParent(root1);
        }
        else if(root2.getRank() > root1.getRank()) {
            root1.setParent(root2);
        }

    }

    public String find(Node node) {
        Node currentNode = node;

        while (currentNode.getParent() != null) {
            currentNode = currentNode.getParent();
        }

        Node rootNode = currentNode;

        currentNode = node;

        while (currentNode != rootNode) {
            Node temp = currentNode.getParent();
            currentNode.setParent(rootNode);
            currentNode = temp;
        }
        return rootNode.getName();
    }

}

public class Krushkal {
    private List<Edge> edges;
    private Map<String, Vertex> vertices;

    public Krushkal() {
        edges = new ArrayList<>();
        vertices = new HashMap<>();
    }

    public void addEdge(String start, String end, double weight) {
        Vertex v1;
        Vertex v2;

        if(this.vertices.get(start) == null) {
            v1 = new Vertex(start);
            this.vertices.put(start, v1);
        }
        else {
            v1 = this.vertices.get(start);
        }

        if(this.vertices.get(end) == null) {
            v2 = new Vertex(end);
            this.vertices.put(end, v2);
        }
        else {
            v2 = this.vertices.get(end);
        }

        Edge edge = new Edge();
        edge.setSource(v1);
        edge.setDestination(v2);
        edge.setWeight(weight);

        this.edges.add(edge);
    }

    public String formatCurrency(double number, String prefix, String suffix, String thousandSeparator) {
        String formatting = "";

        if(!prefix.isEmpty()) {
            formatting =  prefix + " ";
        }

        formatting = formatting + "%";

        if(!thousandSeparator.isEmpty()) {
            formatting = formatting + thousandSeparator;
        }

        formatting = formatting + "f";

        if(!suffix.isEmpty()) {
            if(suffix.equals("%")) {
                formatting = formatting + " %" + suffix;
            }
            else {
                formatting = formatting + " " + suffix;
            }

        }

        return String.format(formatting, number);

    }

    public static void main(String[] args) {


        Krushkal graph = new Krushkal();
        graph.formatCurrency(123456, "$", "", ",");
        //graph.formatCurrency(123456, "", "%", ",");
        graph.addEdge("0", "1", 10);
        graph.addEdge("0", "2", 6);
        graph.addEdge("0", "3", 5);
        graph.addEdge("1", "3", 15);
        graph.addEdge("2", "3", 4);
        graph.spanning();
    }

    public void spanning() {
        //sort the edges first
        Collections.sort(this.edges);
        DisjointSet ds = new DisjointSet(this.vertices);

        List<Edge> mst = new ArrayList<>();

        for (Edge edge : this.edges) {
            Vertex v1 = edge.getSource();
            Vertex v2 = edge.getDestination();

            if(ds.find(v1.getNode()) != ds.find(v2.getNode())) {
                mst.add(edge);
                ds.union(v1.getNode(), v2.getNode());
            }
        }

        for(Edge edge : mst) {
            System.out.println(edge.getSource().getName() + " -> " + edge.getDestination().getName() + ", weight: " + edge.getWeight());
        }
    }
}