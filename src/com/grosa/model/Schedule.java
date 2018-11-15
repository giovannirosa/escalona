package com.grosa.model;

import java.util.ArrayList;
import java.util.List;

public class Schedule {

    private int id;
    private List<Integer> transactions;
    private String serial;
    private String visao;

    public Schedule(int id) {
        this.id = id;
        this.transactions = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", transactions=" + transactions +
                ", serial='" + serial + '\'' +
                ", visao='" + visao + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getVisao() {
        return visao;
    }

    public void setVisao(String visao) {
        this.visao = visao;
    }

    public List<Integer> getTransactions() {
        return transactions;
    }

    public void addTransaction(int t) {
        this.transactions.add(t);
    }

    public boolean hasId(int id) {
        for (int t : transactions) {
            if (t == id)
                return true;
        }
        return false;
    }
}
