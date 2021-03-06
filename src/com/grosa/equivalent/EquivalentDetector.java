package com.grosa.equivalent;

import com.grosa.equivalent.model.Vision;
import com.grosa.equivalent.permutation.PermutationIterator;
import com.grosa.equivalent.permutation.RecursiveCounter;
import com.grosa.model.Schedule;
import com.grosa.model.Transaction;

import java.util.*;
import java.util.stream.Collectors;

public class EquivalentDetector {
    /**
     * Verifica se os escalonamentos são seriais ou não utilizando o teste de equivalência por visão
     * @param transactionList: lista de transações
     * @param scheduleList: lista de escalonamentos
     */
    public static void detect(List<Transaction> transactionList, List<Schedule> scheduleList) {
        List<Vision> allVisions = new ArrayList<>();
        for (Schedule s : scheduleList) {
            final Set<Integer> schedulesIds = s.getTransactions().keySet();
            final Vision vision = new Vision(scheduleList.indexOf(s));
            // Agrupa transações por id
            Map<Integer,List<Transaction>> transactions = transactionList.stream()
                    // Filtra apenas as que pertencem ao escalonamento atual
                    .filter(t -> schedulesIds.contains(t.getId())
                            && t.getTempoChegada() <= s.getFinalTime()
                            && t.getTempoChegada() >= s.getInitTime())
                    .peek(t -> {
                        // Guarda relaçoes de Read-Write da visão original
                        if (t.getOperacao().equals("R")) {
                            for (int i = transactionList.indexOf(t); i < transactionList.size(); ++i) {
                                Transaction aux = transactionList.get(i);
                                // Filtra apenas as que pertencem ao escalonamento atual
                                if (aux.getTempoChegada() < s.getInitTime()
                                        || aux.getTempoChegada() > s.getFinalTime())
                                    continue;
                                if (aux.getId() != t.getId() &&
                                        aux.getOperacao().equals("W") &&
                                        t.getAtributo().equals(aux.getAtributo()))
                                    vision.putReadWriteRelation(t,aux);
                            }
                        }
                        // Guarda última operação de escrita por atributo
                        if (t.getOperacao().equals("W"))
                            vision.putLastWrite(t);
                    })
                    .collect(Collectors.groupingBy(Transaction::getId,LinkedHashMap::new,Collectors.toList()));
            vision.setTransactions(transactions);
//            System.out.println(transactions);
            allVisions.add(vision);
        }

        OUTER: for (Vision v : allVisions) {
            // Utiliza um Iterator para gerar as permutações possíveis
            PermutationIterator<Integer> itPerm = new PermutationIterator<>(v.getOrder(),
                    new RecursiveCounter<>(v.getOrder().size()));
            while (itPerm.hasNext()) {
                v.reOrder(itPerm.next());
//                System.out.println(v.getOrder());
                Map<String,Transaction> lastWrites = findLastWrite(v.getTimeline());
                Map<Transaction,Transaction> readWriteRelation = findReadWriteRelation(v.getTimeline());
                // Se as últimas escritas por atributo e as relações Read-Write se mantiverem como na visão original
                // a visão gerada é equivalente, logo o escalonamento é serializável por visão
                if (lastWrites.equals(v.getLastWrites()) && readWriteRelation.equals(v.getReadWriteRelation())) {
                    scheduleList.get(v.getSchedule()).setVisao("SV");
                    continue OUTER;
                }
            }
        }
    }

    /**
     * Encontra última escrita por atributo da visão especificada
     * @param timeline: lista de transações de forma linear
     */
    private static Map<String,Transaction> findLastWrite(List<Transaction> timeline) {
        Map<String,Transaction> lastWrites = new LinkedHashMap<>();
        for (Transaction t : timeline)
            if (t.getOperacao().equals("W"))
                lastWrites.put(t.getAtributo(),t);
        return lastWrites;
    }

    /**
     * Encontra relações Read-Write da visão especificada
     * @param timeline: lista de transações de forma linear
     */
    private static Map<Transaction,Transaction> findReadWriteRelation(List<Transaction> timeline) {
        Map<Transaction,Transaction> readWriteRelation = new LinkedHashMap<>();
        for (Transaction t : timeline) {
            if (t.getOperacao().equals("R")) {
                for (int i = timeline.indexOf(t); i < timeline.size(); ++i) {
                    Transaction aux = timeline.get(i);
                    if (aux.getId() != t.getId() &&
                            aux.getOperacao().equals("W") &&
                            t.getAtributo().equals(aux.getAtributo()))
                        readWriteRelation.put(t,aux);
                }
            }
        }
        return readWriteRelation;
    }
}
