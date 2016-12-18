package com.Basics;

import java.util.*;

/**
 * Created by YSingh on 06/08/16.
 */
public class Node {
    private int id;
    private Set<Integer> adjacencySet = new HashSet<>();

    public Node(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void addEdge(int id) {
        this.adjacencySet.add(id);
    }

    public List<Integer> getAdjacentVertices() {
        List<Integer> list = new ArrayList<>(this.adjacencySet);
        Collections.sort(list);

        return list;
    }
}
