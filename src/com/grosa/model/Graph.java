package com.grosa.model;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private String nome;
    private List<Vertex> nodos;
    private int vertices;
    private int arestas;

    public Graph(String nome) {
        this.nome = nome;
        this.nodos = new ArrayList<>();
        this.vertices = 0;
        this.arestas = 0;
    }

    public boolean hasCycle() {
        List<Edge> edges = new ArrayList<>();
        for (Vertex n : getNodos()) {
            edges.addAll(n.getVizinhos());
        }
        for (Edge e1 : edges) {
            for (Edge e2 : edges) {
                if (e1.getStartPoint().getTransaction() == e2.getEndPoint().getTransaction() &&
                        e1.getEndPoint().getTransaction() == e2.getStartPoint().getTransaction()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean allCommitted() {
        for (Vertex n : getNodos()) {
            if (!n.isCommitted())
                return false;
        }
        return true;
    }

    public Vertex findNode(int id) {
        for (Vertex n : getNodos()) {
            if (n.getTransaction() == id)
                return n;
        }
        return null;
    }

    public boolean hasTransaction(Transaction t) {
        for (Vertex n : getNodos()) {
            if (n.getTransaction() == t.getId())
                return true;
        }
        return false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Vertex> getNodos() {
        return nodos;
    }

    public void addNodo(Vertex n) {
        this.nodos.add(n);
        ++vertices;
    }

    public int getVertices() {
        return vertices;
    }

    public void setVertices(int vertices) {
        this.vertices = vertices;
    }

    public int getArestas() {
        return arestas;
    }

    public void setArestas(int arestas) {
        this.arestas = arestas;
    }
}
