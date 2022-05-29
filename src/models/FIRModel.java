package models;

import db.query.Condition;
import db.query.DeleteQuery;
import db.query.InsertQuery;
import db.query.SelectQuery;
import entities.FIR;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FIRModel {
    private FIRModel() {}

    public static boolean save(FIR fir) {
        InsertQuery insertQuery = new InsertQuery(
                "FIR",
                List.of(
                        fir.FIR_id,
                        fir.Police_id,
                        fir.Criminal_id,
                        fir.date.getYear() + "-" + fir.date.getMonth() + "-" + fir.date.getDate(),
                        fir.Location,
                        fir.Severity,
                        fir.crime

                )
        );
        try {
            insertQuery.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static ArrayList<FIR> findAll() {
        ArrayList<FIR> list = new ArrayList<>();
        SelectQuery selectQuery = new SelectQuery(
                "FIR",
                null,
                null
        );
        try {
            ResultSet resultSet = selectQuery.execute();

            while (resultSet.next()) {
                FIR fir = new FIR(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getDate(4),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        resultSet.getString(7)
                );

                list.add(fir);
            }
            resultSet.close();
            return list;
        } catch (SQLException e) {
            return null;
        }
    }

    public static boolean delete(FIR fir) {
        DeleteQuery deleteQuery = new DeleteQuery(
                "FIR",
                new Condition("FIR_id = " + fir.FIR_id)
        );

        try {
            int rowsDeleted = deleteQuery.execute();
            return rowsDeleted != 0;
        } catch (SQLException e) {
            return false;
        }
    }

}
