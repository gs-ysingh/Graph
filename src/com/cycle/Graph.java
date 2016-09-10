package com.cycle;

import java.util.*;

/**
 * Created by YSingh on 05/09/16.
 */
class Node {
    String name;
    Set<Node> adjacencySet = new HashSet<>();
    boolean visited = false;
    boolean inRecursionStack = false;

    public boolean isInRecursionStack() {
        return inRecursionStack;
    }

    public void setInRecursionStack(boolean inRecursionStack) {
        this.inRecursionStack = inRecursionStack;
    }

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

class Graph {
    List<Node> nodes;

    public void dfs(Node node) {
        node.setVisited(true);
        node.setInRecursionStack(true);
        for(Node v: node.getAdjacentNodes()) {
            if(v.isInRecursionStack()) {
                System.out.println("Cycle");
            }
            if(!v.isVisited()) {
                this.dfs(v);
            }
        }
        node.setInRecursionStack(false);
    }

    public Graph() {
        this.nodes = new ArrayList<>();
    }

    public void isCycle() {
        for(Node node : this.nodes) {
            dfs(node);
        }
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


        node1.addEdge(node2); //to check cycle

        graph.isCycle();
    }
}
