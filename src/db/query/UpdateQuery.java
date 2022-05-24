package db.query;

import db.Database;

import java.sql.ResultSet;
import java.util.List;

public class UpdateQuery {
    final private String tableName;
    final private List<String> keysValues;
    final private Condition condition;

    // Expects correct use of single quotes in keysValues
    public UpdateQuery(String tableName, List<String> keysValues, Condition condition) {
        this.tableName = tableName;
        this.keysValues = keysValues;
        this.condition = condition;
    }

    public int execute() {
        return Database.getInstance().executeUpdate(toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(tableName);
        sb.append(" SET ");
        for (int i = 0; i < keysValues.size(); i++) {
            sb.append(keysValues.get(i));
            if(i != keysValues.size() - 1)
                sb.append(", ");
        }
        if(condition != null) {
            sb.append(" WHERE ");
            sb.append(condition);
        }

        sb.append(';');
        return sb.toString();
    }
}
