package models;

import db.query.Condition;
import entities.Police;

import java.util.ArrayList;
import java.util.List;

public class PoliceModel {
    private PoliceModel() {}

    public static boolean save(Police police) {
        return true;
    }

/*
    public static List<Police> find(Condition condition) {
        List<Police> list = new ArrayList<>();

    }
*/

}
