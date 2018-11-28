package com.grosa.serial;

import com.grosa.serial.model.Graph;
import com.grosa.model.Schedule;
import com.grosa.model.Transaction;
import com.grosa.serial.model.Vertex;

import java.util.ArrayList;
import java.util.List;

public class SerialDetector {

    /**
     * Separa as transações em diferentes escalonamentos ao mesmo tempo que cria um grafo e
     * faz o teste de conflito, detectando um ciclo ou não.
     * @param transactionList: lista de transações
     * @param scheduleList: lista de escalonamentos
     */
    public static void detect(List<Transaction> transactionList, List<Schedule> scheduleList) {
        int id = 1;
        Schedule s = new Schedule(id);
        // Tempo de início do escalonamento
        s.setInitTime(1);
        Graph g = new Graph("serial");
        List<Transaction> operations = new ArrayList<>();
        for (Transaction tj : transactionList) {
            // Se grafo ainda não tiver transação, adiciona um novo Vértice
            if (!g.hasTransaction(tj)) {
                g.addNodo(new Vertex(g,tj.getId()));
            }

            // Detecta leitura após escrita
            if (tj.getOperacao().equals("R")) {
                for (Transaction ti : operations) {
                    if (ti.getId() != tj.getId() && ti.getOperacao().equals("W") &&
                            tj.getAtributo().equals(ti.getAtributo())) {
                        g.findNode(ti.getId()).addVizinho(g.findNode(tj.getId()));
                    }
                }
            }
            // Detecta escrita após leitura
            if (tj.getOperacao().equals("W")) {
                for (Transaction ti : operations) {
                    if (ti.getId() != tj.getId() && ti.getOperacao().equals("R") &&
                            tj.getAtributo().equals(ti.getAtributo())) {
                        g.findNode(ti.getId()).addVizinho(g.findNode(tj.getId()));
                    }
                }
            }
            // Detecta escrita após escrita
            if (tj.getOperacao().equals("W")) {
                for (Transaction ti : operations) {
                    if (ti.getId() != tj.getId() && ti.getOperacao().equals("W") &&
                            tj.getAtributo().equals(ti.getAtributo())) {
                        g.findNode(ti.getId()).addVizinho(g.findNode(tj.getId()));
                    }
                }
            }

            // Guarda transação nas operações do escalonamento atual
            operations.add(tj);
            // Verifica operação de commit
            if (tj.getOperacao().equals("C")) {
                g.findNode(tj.getId()).setCommitted(true);
            }
            // Se todos os Vértices do grafo estão comitados, o escalonamento atual terminou
            if (g.allCommitted()) {
                // Adiciona as transações do grafo no escalonamento atual
                for (Vertex n : g.getNodos()) {
                    s.addTransaction(n.getTransaction());
                }
                // Se houver ciclo, não serial, caso contrário, serial
                if (g.hasCycle())
                    s.setSerial("NS");
                else
                    s.setSerial("SS");
                // Tempo de término do escalonamento
                s.setFinalTime(tj.getTempoChegada());
                scheduleList.add(s);
                // Novo escalonamento inicia
                s = new Schedule(++id);
                // Tempo de início do escalonamento
                s.setInitTime(tj.getTempoChegada()+1);
                g = new Graph("serial");
                operations.clear();
            }
        }
    }
}
