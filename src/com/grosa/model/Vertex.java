package com.grosa.model;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private int transaction;
    private boolean committed;
    private int grau;
    private List<Edge> vizinhos;

    public Vertex(int transaction) {
        this.transaction = transaction;
        this.committed = false;
        this.grau = 0;
        this.vizinhos = new ArrayList<>();
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
    }
}
