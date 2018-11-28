package com.grosa.serial.model;

import com.grosa.model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Classe para representar um grafo, com nome, vértices e informação
 * de quantidade de vértices e arestas.
 */
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

    /**
     * Detecta um ciclo no grafo.
     */
    private boolean detectCycle(Vertex root) {

        for (Vertex vertex : getNodos()) {
            vertex.setBeingVisited(false);
            vertex.setVisited(false);
        }

        return dfs(root);

    }

    /**
     * Implementa uma busca em profundidade para encontrar um ciclo.
     */
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

    /**
     * Verifica se todos os vértices foram confirmados.
     * @return boolean
     */
    public boolean allCommitted() {
        for (Vertex n : getNodos()) {
            if (!n.isCommitted())
                return false;
        }
        return true;
    }

    /**
     * Encontra nó com id especificado.
     * @param id: transação
     * @return Vertex
     */
    public Vertex findNode(int id) {
        for (Vertex n : getNodos()) {
            if (n.getTransaction() == id)
                return n;
        }
        return null;
    }

    /**
     * Verifica se já possui vértice com transação especificada.
     * @param t: transação
     * @return boolean
     */
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
