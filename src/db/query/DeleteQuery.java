package db.query;

import db.Database;

import java.sql.SQLException;

public class DeleteQuery {
    final private String tableName;
    final private Condition condition;

    public DeleteQuery(String tableName, Condition condition) {
        this.tableName = tableName;
        this.condition = condition;
    }

    public int execute() throws SQLException {
        return Database.getInstance().executeUpdate(toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(tableName);
        if(condition != null) {
            sb.append(" WHERE ");
            sb.append(condition);
        }
        sb.append(';');
        return sb.toString();
    }
}
