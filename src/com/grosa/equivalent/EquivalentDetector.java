package com.grosa.equivalent;

import com.grosa.equivalent.model.Vision;
import com.grosa.model.Schedule;
import com.grosa.model.Transaction;

import java.util.*;

public class EquivalentDetector {
    public static void detect(List<Transaction> transactionList, List<Schedule> scheduleList) {
        List<Vision> allVisions = new ArrayList<>();
        for (Schedule s : scheduleList) {
            s.setVisao("NV");
            List<Integer> schedulesIds = s.getTransactions();
            Transaction lastWrite = null;
            Vision vision = new Vision(scheduleList.indexOf(s));
            for (Transaction t : transactionList) {
                if (!schedulesIds.contains(t.getId()))
                    continue;
                List<Transaction> list = vision.getTransactions().get(t.getId());
                if (list == null) {
                    list = new ArrayList<>();
                    vision.getTransactions().put(t.getId(), list);
                }
                list.add(t);
                if (t.getOperacao().equals("R") && vision.getFirstRead() == null)
                    vision.setFirstRead(t);
                if (t.getOperacao().equals("W") && vision.getFirstWrite() == null)
                    vision.setFirstWrite(t);
                if (t.getOperacao().equals("W"))
                    vision.setLastWrite(t);
            }
            System.out.println(vision);
            allVisions.add(vision);
        }

        for (Vision v : allVisions) {
            Transaction lastWrite = findLastWrite(v.getTransactions().values());
            Transaction firstRead = findFirstRead(v.getTransactions().values());
            Transaction firstWrite = findFirstWrite(v.getTransactions().values());
            if (lastWrite != v.getLastWrite() || firstRead != v.getFirstRead()
                    || firstWrite != v.getLastWrite())
                continue;
            scheduleList.get(v.getSchedule()).setVisao("SV");
        }
    }

    private static Transaction findLastWrite(Collection<List<Transaction>> values) {
        Transaction lastWrite = null;
        for (List<Transaction> list : values) {
            for (Transaction t : list) {
                if (t.getOperacao().equals("W"))
                    lastWrite = t;
            }
        }
        return lastWrite;
    }

    private static Transaction findFirstRead(Collection<List<Transaction>> values) {
        for (List<Transaction> list : values) {
            for (Transaction t : list) {
                if (t.getOperacao().equals("R"))
                    return t;
            }
        }
        return null;
    }

    private static Transaction findFirstWrite(Collection<List<Transaction>> values) {
        for (List<Transaction> list : values) {
            for (Transaction t : list) {
                if (t.getOperacao().equals("W"))
                    return t;
            }
        }
        return null;
    }
}
