import db.query.InsertQuery;
import db.query.SelectQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
//        System.out.println("Hello world!");
        InsertQuery iq = new InsertQuery(
                "emp",
                List.of(12, "EMP12")
        );
        iq.execute();

        SelectQuery sq = new SelectQuery("emp", null, null);

        ResultSet rs = sq.execute();
        while(rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2));
        }
    }
}