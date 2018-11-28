package com.grosa.serial.model;

import com.grosa.model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    @Override
    public String toString() {
        return "Graph{" +
                "nome='" + nome + '\'' +
                ", nodos=" + nodos +
                ", vertices=" + vertices +
                ", arestas=" + arestas +
                '}';
    }

    public boolean hasCycle() {

        for (Vertex vertex : getNodos()) {
            if (detectCycle(vertex)) {
                return true;
            }
        }

        return false;

    }

    private boolean detectCycle(Vertex root) {

        for (Vertex vertex : getNodos()) {
            vertex.setBeingVisited(false);
            vertex.setVisited(false);
        }

        return dfs(root);

    }

    private boolean dfs(Vertex root) {
        root.setBeingVisited(true);

        for (Edge edge : root.getVizinhos()) {
            Vertex neighborVertex = edge.getEndPoint();
            if (neighborVertex.isBeingVisited()) {
                return true;
            } else if (!neighborVertex.isVisited() && dfs(neighborVertex)) {
                return true;
            }
        }

        root.setVisited(true);
        root.setBeingVisited(false);
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
