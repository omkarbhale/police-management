package models;

import db.query.Condition;
import db.query.DeleteQuery;
import db.query.InsertQuery;
import db.query.SelectQuery;
import entities.Police;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PoliceModel {
    private PoliceModel() {}

    public static boolean save(Police police) {
        InsertQuery insertQuery = new InsertQuery(
                "police_staff",
                List.of(
                        police.Police_id,
                        police.firstName,
                        police.middleName,
                        police.lastName,
                        police.post,
                        police.DOB,
                        police.contactNo,
                        police.address

                )
        );
        try {
            insertQuery.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }


    public static ArrayList<Police> findAll() {
        ArrayList<Police> list = new ArrayList<>();
        SelectQuery selectQuery = new SelectQuery(
                "police_staff",
                null,
                null
        );

        try {
            ResultSet resultSet = selectQuery.execute();

            while (resultSet.next()) {
                Police police = new Police(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDate(6),
                        resultSet.getString(7),
                        resultSet.getString(8)
                );

                list.add(police);
            }
            resultSet.close();
            return list;
        } catch (SQLException e) {
            return null;
        }
    }

    public static boolean delete(Police police) {
        DeleteQuery deleteQuery = new DeleteQuery(
                "police_staff",
                new Condition("Police_id = " + police.Police_id)
        );

        try {
            int rowsDeleted = deleteQuery.execute();
            return rowsDeleted != 0;
        } catch (SQLException e) {
            return false;
        }
    }

}
