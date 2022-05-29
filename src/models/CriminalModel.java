package models;

import db.query.Condition;
import db.query.DeleteQuery;
import db.query.InsertQuery;
import db.query.SelectQuery;
import entities.Criminal;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CriminalModel {

    private CriminalModel(){}

    public static boolean save(Criminal criminal){
        InsertQuery insertQuery = new InsertQuery(
                "Criminal",
                List.of(
                        criminal.Criminal_id,
                        criminal.firstname,
                        criminal.middlename,
                        criminal.lastname,
                        criminal.DOB.getYear() + "-" + criminal.DOB.getMonth() + "-" + criminal.DOB.getDate(),
                        criminal.address
                )
        );
        try {
            insertQuery.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static ArrayList<Criminal> findAll() {
        ArrayList<Criminal> list = new ArrayList<>();
        SelectQuery selectQuery = new SelectQuery(
                "Criminal",
                null,
                null
        );

        try {
            ResultSet resultSet = selectQuery.execute();

            while (resultSet.next()) {
                Criminal criminal = new Criminal(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5),
                        resultSet.getString(6)
                );

                list.add(criminal);
            }
            resultSet.close();
            return list;
        } catch (SQLException e) {
            return null;
        }
    }

    public static boolean delete(Criminal criminal) {
        DeleteQuery deleteQuery = new DeleteQuery(
                "Criminal",
                new Condition("Criminal_id = " + criminal.Criminal_id)
        );

        try {
            int rowsDeleted = deleteQuery.execute();
            return rowsDeleted != 0;
        } catch (SQLException e) {
            return false;
        }
    }
}
