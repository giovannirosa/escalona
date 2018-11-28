package com.grosa.serial;

import com.grosa.serial.model.Graph;
import com.grosa.model.Schedule;
import com.grosa.model.Transaction;
import com.grosa.serial.model.Vertex;

import java.util.ArrayList;
import java.util.List;

public class SerialDetector {
    public static void detect(List<Transaction> transactionList, List<Schedule> scheduleList) {
        int id = 1;
        Schedule s = new Schedule(id);
        s.setInitTime(1);
        Graph g = new Graph("serial");
        List<Transaction> operations = new ArrayList<>();
        for (Transaction tj : transactionList) {
            if (!g.hasTransaction(tj)) {
                g.addNodo(new Vertex(g,tj.getId()));
            }

            if (tj.getOperacao().equals("R")) {
                for (Transaction ti : operations) {
                    if (ti.getId() != tj.getId() && ti.getOperacao().equals("W") &&
                            tj.getAtributo().equals(ti.getAtributo())) {
                        g.findNode(ti.getId()).addVizinho(g.findNode(tj.getId()));
                    }
                }
            }
            if (tj.getOperacao().equals("W")) {
                for (Transaction ti : operations) {
                    if (ti.getId() != tj.getId() && ti.getOperacao().equals("R") &&
                            tj.getAtributo().equals(ti.getAtributo())) {
                        g.findNode(ti.getId()).addVizinho(g.findNode(tj.getId()));
                    }
                }
            }
            if (tj.getOperacao().equals("W")) {
                for (Transaction ti : operations) {
                    if (ti.getId() != tj.getId() && ti.getOperacao().equals("W") &&
                            tj.getAtributo().equals(ti.getAtributo())) {
                        g.findNode(ti.getId()).addVizinho(g.findNode(tj.getId()));
                    }
                }
            }

            operations.add(tj);
            if (tj.getOperacao().equals("C")) {
                g.findNode(tj.getId()).setCommitted(true);
            }
            if (g.allCommitted()) {
                for (Vertex n : g.getNodos()) {
                    s.addTransaction(n.getTransaction());
                }
                if (g.hasCycle())
                    s.setSerial("NS");
                else
                    s.setSerial("SS");
                s.setFinalTime(tj.getTempoChegada());
                scheduleList.add(s);
                s = new Schedule(++id);
                s.setInitTime(tj.getTempoChegada()+1);
                g = new Graph("serial");
                operations.clear();
            }
        }
    }
}
