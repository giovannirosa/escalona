package com.grosa;

import com.grosa.model.Graph;
import com.grosa.model.Vertex;
import com.grosa.model.Schedule;
import com.grosa.model.Transaction;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        List<Transaction> transactionList = collectData();
        System.out.println("Data collected:");
        transactionList.forEach(t -> System.out.println(t));

        List<Schedule> scheduleList = serialDetect(transactionList);
        scheduleList.forEach(s -> System.out.println(s));

//        System.out.println("Building schedules...");
//        List<Schedule> scheduleList = new ArrayList<>();
//        Schedule s = new Schedule();
//        for (Transaction t : transactionList) {
//            if (!s.hasId(t.getId())) {
//                s.addTransaction(t);
//            }
//            if (t.getOperacao().equals("C")) {
//
//            }
//        }
    }

    private static List<Schedule> serialDetect(List<Transaction> transactionList) {
        int id = 1;
        List<Schedule> scheduleList = new ArrayList<>();
        Schedule s = new Schedule(id);
        Graph g = new Graph("serial");
        List<Transaction> operations = new ArrayList<>();
        for (Transaction tj : transactionList) {
            if (!g.hasTransaction(tj)) {
                g.addNodo(new Vertex(tj.getId()));
            }

            if (tj.getOperacao().equals("R")) {
                for (Transaction ti : operations) {
                    if (ti.getOperacao().equals("W") && tj.getAtributo().equals(ti.getAtributo())) {
                        g.findNode(ti.getId()).addVizinho(g.findNode(tj.getId()));
                    }
                }
            }
            if (tj.getOperacao().equals("W")) {
                for (Transaction ti : operations) {
                    if (ti.getOperacao().equals("R") && tj.getAtributo().equals(ti.getAtributo())) {
                        g.findNode(ti.getId()).addVizinho(g.findNode(tj.getId()));
                    }
                }
            }
            if (tj.getOperacao().equals("W")) {
                for (Transaction ti : operations) {
                    if (ti.getOperacao().equals("W") && tj.getAtributo().equals(ti.getAtributo())) {
                        g.findNode(ti.getId()).addVizinho(g.findNode(tj.getId()));
                    }
                }
            }

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
                scheduleList.add(s);
                s = new Schedule(++id);
                g = new Graph("serial");
            }
        }
        return scheduleList;
    }

    private static List<Transaction> collectData() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("entrada"));//System.in
        System.out.println("Printing the file passed in:");
        List<Transaction> transactionList = new ArrayList<>();
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            System.out.println(line);
            String[] param = line.split(" ");
            transactionList.add(new Transaction(Integer.valueOf(param[0]),Integer.valueOf(param[1]),param[2],param[3]));
        }
        return transactionList;
    }
}
