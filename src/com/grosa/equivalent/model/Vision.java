package com.grosa.equivalent.model;

import com.grosa.model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vision {
    int schedule;
    Map<Integer,List<Transaction>> transactions;
    Transaction lastWrite;
    Transaction firstRead;
    Transaction firstWrite;

    public Vision(int schedule) {
        this.schedule = schedule;
        transactions = new HashMap<>();
    }

    public Transaction getFirstRead() {
        return firstRead;
    }

    public void setFirstRead(Transaction firstRead) {
        this.firstRead = firstRead;
    }

    public Transaction getFirstWrite() {
        return firstWrite;
    }

    public int getSchedule() {
        return schedule;
    }

    public void setFirstWrite(Transaction firstWrite) {
        this.firstWrite = firstWrite;
    }

    @Override
    public String toString() {
        return "Vision{" +
                "transactions=" + transactions +
                ", lastWrite=" + lastWrite +
                '}';
    }

    public Map<Integer, List<Transaction>> getTransactions() {
        return transactions;
    }

    public void setTransactions(Map<Integer, List<Transaction>> transactions) {
        this.transactions = transactions;
    }

    public Transaction getLastWrite() {
        return lastWrite;
    }

    public void setLastWrite(Transaction lastWrite) {
        this.lastWrite = lastWrite;
    }
}
