package com.benmyers.dezeus.core;

import com.benmyers.dezeus.core.justification.Justification;
import com.benmyers.dezeus.core.justification.RuleJustification;

public class Deduction {

    private Statement statement;
    private Justification justification;

    public Deduction(Statement statement, Justification justification) {
        this.statement = statement;
        this.justification = justification;
    }

    public Statement getStatement() {
        return statement;
    }

    public Justification getJustification() {
        return justification;
    }

    public void printTrace(int depth) {
        String indent = new String(new char[depth]).replace("\0", " ");
        System.out.println(indent + toString());
        if (getJustification() instanceof RuleJustification) {
            RuleJustification ruleJustification = (RuleJustification) getJustification();
            for (Deduction deduction : ruleJustification.getEvidence())
                deduction.printTrace(depth + 1);
        }
    }

    @Override
    public String toString() {
        return statement.toString() + " (" + justification.getAbbr() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deduction) {
            Deduction d = (Deduction) obj;
            return d.getStatement().equals(statement);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return statement.hashCode();
    }
}
