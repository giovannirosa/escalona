package com.grosa.model;

import java.util.List;

public class Graph {

    private String nome;
    private List<Node> nodos;
    private int vertices;
    private int arestas;

    public Graph(String nome, List<Node> nodos, int vertices, int arestas) {
        this.nome = nome;
        this.nodos = nodos;
        this.vertices = vertices;
        this.arestas = arestas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Node> getNodos() {
        return nodos;
    }

    public void setNodos(List<Node> nodos) {
        this.nodos = nodos;
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
