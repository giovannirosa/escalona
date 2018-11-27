package com.grosa;

import com.grosa.equivalent.EquivalentDetector;
import com.grosa.model.Schedule;
import com.grosa.model.Transaction;
import com.grosa.serial.SerialDetector;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        List<Transaction> transactionList = collectData();
        System.out.println("Data collected:");
        transactionList.forEach(t -> System.out.println(t));

        List<Schedule> scheduleList = new ArrayList<>();
        SerialDetector.detect(transactionList,scheduleList);
        EquivalentDetector.detect(transactionList,scheduleList);

        scheduleList.forEach(s -> System.out.println(s));
    }

    private static List<Transaction> collectData() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("entrada"));//System.in
        System.out.println("Printing the file passed in:");
        List<Transaction> transactionList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            System.out.println(line);
            String[] param = line.split(" ");
            transactionList.add(new Transaction(Integer.valueOf(param[0]), Integer.valueOf(param[1]), param[2], param[3]));
        }
        return transactionList;
    }
}
