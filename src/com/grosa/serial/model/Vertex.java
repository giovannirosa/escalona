package com.grosa.serial.model;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private Graph parent;
    private int transaction;
    private boolean committed;
    private boolean visited;
    private boolean beingVisited;
    private int grau;
    private List<Edge> vizinhos;

    public Vertex(Graph parent, int transaction) {
        this.parent = parent;
        this.transaction = transaction;
        this.committed = false;
        this.grau = 0;
        this.vizinhos = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "transaction=" + transaction +
                ", committed=" + committed +
                ", grau=" + grau +
                ", vizinhos=" + vizinhos +
                '}';
    }

    public boolean isBeingVisited() {
        return beingVisited;
    }

    public void setBeingVisited(boolean beingVisited) {
        this.beingVisited = beingVisited;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isCommitted() {
        return committed;
    }

    public void setCommitted(boolean committed) {
        this.committed = committed;
    }

    public int getTransaction() {
        return transaction;
    }

    public void setTransaction(int transaction) {
        this.transaction = transaction;
    }

    public int getGrau() {
        return grau;
    }

    public void setGrau(int grau) {
        this.grau = grau;
    }

    public List<Edge> getVizinhos() {
        return vizinhos;
    }

    public void addVizinho(Vertex viz) {
        this.vizinhos.add(new Edge(this,viz));
        parent.setArestas(parent.getArestas()+1);
    }
}
