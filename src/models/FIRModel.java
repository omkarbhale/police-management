package models;

import entities.FIR;

import java.sql.Date;
import java.util.ArrayList;

public class FIRModel {
    private FIRModel() {}

    public static boolean save(FIR fir) {
        return true;
    }

    public static boolean delete(FIR fir) {
        return true;
    }

    public static ArrayList<FIR> findAll() {
        ArrayList<FIR> list = new ArrayList<>();
        FIR f = new FIR(1, 1, 1, Date.valueOf("1970-01-01"), "1", 1, "Stole my heart");
        for(int i = 0; i < 7; i++) {
            list.add(f);
        }
        return list;
    }
}
