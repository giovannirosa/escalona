package com.grosa;

import com.grosa.model.Schedule;
import com.grosa.model.Transaction;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("entrada"));//System.in
        System.out.println("Printing the file passed in:");
        List<Transaction> transactionList = new ArrayList<>();
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            System.out.println(line);
            String[] param = line.split(" ");
            transactionList.add(new Transaction(Integer.valueOf(param[0]),Integer.valueOf(param[1]),param[2],param[3]));
        }
        System.out.println("Data collected:");
        transactionList.stream().forEach(t -> System.out.println(t));

        System.out.println("Building schedules...");
        List<Schedule> scheduleList = new ArrayList<>();
        Schedule s = new Schedule();
        for (Transaction t : transactionList) {
            if (!s.hasId(t.getId())) {
                s.addTransaction(t);
            }
            if (t.getOperacao().equals("C")) {

            }
        }
    }
}
