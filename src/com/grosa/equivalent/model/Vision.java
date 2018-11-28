package com.grosa.equivalent.model;

import com.grosa.model.Transaction;

import java.util.*;
import java.util.stream.Collectors;

public class Vision {
    int schedule;
    Map<Integer,List<Transaction>> transactions;
    List<Integer> order;
    List<Transaction> timeline;
    Map<String,Transaction> lastWrites;
    Map<Transaction,Transaction> readWriteRelation;

    @Override
    public String toString() {
        return "Vision{" +
                "schedule=" + schedule +
                ", transactions=" + transactions +
                ", timeline=" + timeline +
                ", lastWrites=" + lastWrites +
                ", readWriteRelation=" + readWriteRelation +
                '}';
    }

    public Vision(int schedule) {
        this.schedule = schedule;
        this.order = new ArrayList<>();
        this.timeline = new ArrayList<>();
        this.lastWrites = new HashMap<>();
        this.readWriteRelation = new HashMap<>();
    }

    public List<Integer> getOrder() {
        return order;
    }

    public Map<String, Transaction> getLastWrites() {
        return lastWrites;
    }

    public void putLastWrite(Transaction t) {
        this.lastWrites.put(t.getAtributo(), t);
    }

    public void putReadWriteRelation(Transaction r, Transaction w) {
        this.readWriteRelation.put(r,w);
    }

    public Map<Transaction, Transaction> getReadWriteRelation() {
        return readWriteRelation;
    }

    public List<Transaction> getTimeline() {
        return timeline;
    }

    public int getSchedule() {
        return schedule;
    }

    public Map<Integer, List<Transaction>> getTransactions() {
        return transactions;
    }

    public void reOrder(List<Integer> newOrder) {
        order = newOrder;
        timeline.clear();
        Map<Integer, List<Transaction>> map = new LinkedHashMap<>();
        order.forEach(i -> map.put(i,transactions.get(i)));
        map.forEach((k,v) -> v.forEach(t -> timeline.add(t)));
        transactions = map;
    }

    public void setTransactions(Map<Integer, List<Transaction>> map) {
        map.forEach((k,v) -> v.forEach(t -> timeline.add(t)));
        map.keySet().forEach(i -> order.add(i));
        transactions = map;
    }
}
