package com.benmyers.dezeus.core;

import com.benmyers.dezeus.core.justification.Justification;

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
