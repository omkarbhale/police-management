package db.query;

import db.Database;

import java.sql.*;
import java.util.List;

public class SelectQuery {
    final private String tableName;
    final private List<String> colNames;
    final private Condition condition;

    public SelectQuery(String tableName, List<String> columnNames, Condition condition) {
        this.tableName = tableName;
        this.colNames = columnNames;
        this.condition = condition;
    }

    public ResultSet execute() {
        return Database.getInstance().executeQuery(toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        if(colNames == null) {
            sb.append("*");
        } else {
            for (int i = 0; i < colNames.size(); i++) {
                if (i == colNames.size() - 1) {
                    sb.append(colNames.get(i));
                } else {
                    sb.append(colNames.get(i)).append(", ");
                }
            }
        }
        sb.append(" FROM ");
        sb.append(tableName);

        if(condition != null) {
            sb.append(" WHERE ");
            sb.append(condition);
        }

        sb.append(';');
        return sb.toString();
    }
}
