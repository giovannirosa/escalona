package com.grosa;

import com.grosa.equivalent.EquivalentDetector;
import com.grosa.model.Schedule;
import com.grosa.model.Transaction;
import com.grosa.serial.SerialDetector;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Giovanni Rosa
 * @version 1.0
 */
public class Main {

    /**
     * Detecta se os escalonamentos são serializáveis ou não fazendo o
     * teste por conflito e o teste de equivalência por visão.
     * @param args: argumentos de entrada
     */
    public static void main(String[] args) throws FileNotFoundException {
        List<Transaction> transactionList = collectData();
//        System.out.println("Data collected:");
//        transactionList.forEach(t -> System.out.println(t));

        List<Schedule> scheduleList = new ArrayList<>();

        // Gera lista de escalonamento e faz o teste por conflito
        SerialDetector.detect(transactionList,scheduleList);
        // Faz o teste de equivalência por visão
        EquivalentDetector.detect(transactionList,scheduleList);

        // Imprime resultados na saída padrão (stdout)
        scheduleList.forEach(s -> {
            // Agrupa transações do escalonamento e ordena
            List<Integer> list = s.getTransactions().keySet().stream()
                                                            .sorted()
                                                            .collect(Collectors.toList());
            // Imprime resultados
            System.out.println(s.getId()+" "
                +list.toString().replaceAll("\\[","")
                                .replaceAll("]","")
                                .replaceAll(" ","")
                +" "+s.getSerial()+" "+s.getVisao());
        });
    }

    /**
     * Coleta os dados da entrada padrão (stdin) e transforma cada linha em Transaction
     * @return lista de transações
     */
    private static List<Transaction> collectData() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);//System.in
//        System.out.println("Printing the file passed in:");
        List<Transaction> transactionList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
//            System.out.println(line);
            String[] param = line.split(" ");
            transactionList.add(new Transaction(Integer.valueOf(param[0]),
                    Integer.valueOf(param[1]), param[2], param[3]));
        }
        return transactionList;
    }
}
