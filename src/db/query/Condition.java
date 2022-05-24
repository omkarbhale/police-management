package db.query;

import java.util.List;

public class Condition {
    private String strCondition;

    public Condition(String strCondition) {
        this.strCondition = strCondition;
    }

    /**
     * Negates the condition
     * Alters the object and does not create a new one
     * returns the same object
     */
    public Condition negate() {
        strCondition = "(NOT " + strCondition + ")";
        return this;
    }

    @Override
    public String toString() {
        return strCondition;
    }

    /**
     * Creates new Condition with AND of the given 2 and returns
     */
    public static Condition AND(Condition c1, Condition c2) {
        return new Condition("(" + c1.strCondition + " AND " + c2.strCondition + ")");
    }

    /**
     * Creates new Condition with OR of the given 2 and returns
     */
    public static Condition OR(Condition c1, Condition c2) {
        return new Condition("(" + c1.strCondition + " OR " + c2.strCondition + ")");
    }

    /**
     * Creates new Condition with AND all the given.
     */
    public static Condition merge(List<Condition> conditions) {
        if(conditions.size() == 0) return null;
        Condition condition = conditions.get(0);
        for(int i = 1; i < conditions.size(); i++) {
            condition = Condition.AND(condition, conditions.get(i));
        }
        return condition;
    }
}
