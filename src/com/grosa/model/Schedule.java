package com.grosa.model;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    List<Transaction> transactions;

    public Schedule() {
        this.transactions = new ArrayList<>();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction t) {
        this.transactions.add(t);
    }

    public boolean hasId(int id) {
        for (Transaction t : transactions) {
            if (t.getId() == id)
                return true;
        }
        return false;
    }
}
