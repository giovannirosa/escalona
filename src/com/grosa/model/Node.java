package com.grosa.model;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String nome;
    private int grau;
    private List<Node> vizinhos;

    public Node(String nome) {
        this.nome = nome;
        this.grau = 0;
        this.vizinhos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getGrau() {
        return grau;
    }

    public void setGrau(int grau) {
        this.grau = grau;
    }

    public List<Node> getVizinhos() {
        return vizinhos;
    }

    public void addVizinho(Node viz) {
        this.vizinhos.add(viz);
    }
}
