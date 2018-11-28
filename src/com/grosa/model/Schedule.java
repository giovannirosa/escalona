package com.grosa.model;

import java.util.*;

public class Schedule {

    private int id;
    private Map<Integer, Boolean> transactions;
    private int finalTime;
    private int initTime;
    private String serial = "NS";
    private String visao = "NV";

    public Schedule(int id) {
        this.id = id;
        this.transactions = new LinkedHashMap<>();
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

    public int getInitTime() {
        return initTime;
    }

    public void setInitTime(int initTime) {
        this.initTime = initTime;
    }

    public int getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(int finalTime) {
        this.finalTime = finalTime;
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

    public Map<Integer, Boolean> getTransactions() {
        return transactions;
    }

    public void addTransaction(int t) {
        this.transactions.put(t,false);
    }

    public void confirmTransaction(int t) {
        this.transactions.put(t,true);
    }

    public boolean allCommitted() {
        for (boolean c : transactions.values())
            if (!c)
                return false;
        return true;
    }

}
