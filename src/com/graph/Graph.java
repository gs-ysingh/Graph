package com.graph;

import java.util.*;

/**
 * Created by YSingh on 05/09/16.
 */

class Node {
    String name;
    Set<Node> adjacencySet = new HashSet<>();
    boolean visited = false;

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addEdge(Node node) {
        this.adjacencySet.add(node);
    }

    public Set<Node> getAdjacentNodes() {
        return this.adjacencySet;
    }
}

class TopologicalSort {
    Stack<Node> stack = new Stack<Node>();
    List<Node> nodes;

    public TopologicalSort(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void dfs() {
        for(Node node : this.nodes) {
            if(!node.visited) {
                this.dfs(node);
            }
        }
    }

    public void dfs(Node node) {
        node.setVisited(true);
        for(Node v: node.getAdjacentNodes()) {
            if(!v.isVisited()) {
                this.dfs(v);
            }
        }
        this.stack.push(node);
    }

    public Stack<Node> getStack() {
        return this.stack;
    }
}

class Graph {
    List<Node> nodes;

    public Graph() {
        this.nodes = new ArrayList<>();
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        Node node0 = new Node("0");
        graph.nodes.add(node0);
        Node node1 = new Node("1");
        graph.nodes.add(node1);
        Node node2 = new Node("2");
        graph.nodes.add(node2);
        Node node3 = new Node("3");
        graph.nodes.add(node3);
        Node node4 = new Node("4");
        graph.nodes.add(node4);
        Node node5 = new Node("5");
        graph.nodes.add(node5);

        node5.addEdge(node2);
        node5.addEdge(node0);
        node4.addEdge(node0);
        node4.addEdge(node1);
        node2.addEdge(node3);
        node3.addEdge(node1);

        TopologicalSort tls = new TopologicalSort(graph.nodes);
        tls.dfs();

        Stack<Node> nodes = tls.getStack();

        while (!nodes.isEmpty()) {
            Node n = nodes.pop();
            System.out.println(n.getName());
        }
    }
}
