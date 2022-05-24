package db.query;

import db.Database;

import java.sql.ResultSet;
import java.util.List;

public class InsertQuery {
    final private String tableName;
    final private List<Object> values;

    public InsertQuery(String tableName, List<Object> values) {
        this.tableName = tableName;
        this.values = values;
    }

    public boolean execute() {
        int rowsAffected = Database.getInstance().executeUpdate(toString());
        return rowsAffected == 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(tableName);
        sb.append(" VALUES(");
        for(int i = 0; i < values.size(); i++) {
            sb.append('\'');
            sb.append(values.get(i).toString());
            sb.append('\'');
            if(i != values.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(");");
        return sb.toString();
    }
}
