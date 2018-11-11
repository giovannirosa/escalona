package com.grosa.model;

public class Transaction {

    private int tempoChegada;
    private int id;
    private String operacao;
    private String atributo;

    public Transaction(int tempoChegada, int id, String operacao, String atributo) {
        this.tempoChegada = tempoChegada;
        this.id = id;
        this.operacao = operacao;
        this.atributo = atributo;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "tempoChegada=" + tempoChegada +
                ", id=" + id +
                ", operacao='" + operacao + '\'' +
                ", atributo='" + atributo + '\'' +
                '}';
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public int getTempoChegada() {
        return tempoChegada;
    }

    public void setTempoChegada(int tempoChegada) {
        this.tempoChegada = tempoChegada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
