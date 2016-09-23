package com.Tarjan;

import java.util.*;

/**
 * Created by YSingh on 24/09/16.
 */
class Vertex {

    private String name;
    private List<Vertex> neighbourList;
    private Vertex predecessor;
    private boolean visited;
    private int lowLink;

    public Vertex(String name) {
        this.name = name;
        this.neighbourList = new ArrayList<>();
    }

    public void addNeighbour(Vertex vertex) {
        this.neighbourList.add(vertex);
    }

    public int getLowLink() {
        return lowLink;
    }

    public void setLowLink(int lowLink) {
        this.lowLink = lowLink;
    }

    public Vertex getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Vertex predecessor) {
        this.predecessor = predecessor;
    }

    public String getName() {
        return name;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public List<Vertex> getNeighbourList() {
        return neighbourList;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

class TarjanAlgorithm {

    private Stack<Vertex> stack;
    private List<Vertex> vertexList;
    private List<List<Vertex>> connectedComponentList;
    private int time = 0;

    public TarjanAlgorithm(List<Vertex> vertexList){
        this.stack = new Stack<>();
        this.vertexList = vertexList;
        this.connectedComponentList = new ArrayList<>();
    }

    public void runAlgorithm() {
        for(Vertex vertex : vertexList){
            if( !vertex.isVisited() ){
                dfs(vertex);
            }
        }
    }

    private void dfs(Vertex vertex){
        vertex.setLowLink(time++);
        vertex.setVisited(true);
        this.stack.add(vertex);
        boolean isComponentRoot = true;

        for(Vertex v : vertex.getNeighbourList()){
            if(!v.isVisited()){
                dfs(v);
            }

            if( vertex.getLowLink() > v.getLowLink() ){
                vertex.setLowLink(v.getLowLink());
                isComponentRoot = false;
            }
        }

        if(isComponentRoot){
            List<Vertex> component = new ArrayList<>();

            while(true){
                Vertex actualVertex = stack.pop();
                component.add(actualVertex);
                actualVertex.setLowLink(Integer.MAX_VALUE);

                if( actualVertex.getName().equals(vertex.getName()) ) break;
            }

            connectedComponentList.add(component);
        }
    }

    public void printComponents(){
        System.out.println(connectedComponentList);
    }
}


public class Tarjan {

    public static void main(String[] args) {
        List<Vertex> vertexList = new ArrayList<>();

        vertexList.add(new Vertex("0"));
        vertexList.add(new Vertex("1"));
        vertexList.add(new Vertex("2"));
        vertexList.add(new Vertex("3"));
        vertexList.add(new Vertex("4"));

        vertexList.get(0).addNeighbour(vertexList.get(2));
        vertexList.get(0).addNeighbour(vertexList.get(3));
        vertexList.get(1).addNeighbour(vertexList.get(0));
        vertexList.get(2).addNeighbour(vertexList.get(1));
        vertexList.get(3).addNeighbour(vertexList.get(4));

        TarjanAlgorithm tarjanAlgorithm = new TarjanAlgorithm(vertexList);
        tarjanAlgorithm.runAlgorithm();
        tarjanAlgorithm.printComponents();
    }
}
