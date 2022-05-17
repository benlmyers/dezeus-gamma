package com.benmyers.dezeus.core;

import com.benmyers.dezeus.core.justification.Justification;

public class Deduction {

    private Statement statement;
    private Justification justification;

    public Deduction(Statement statement, Justification justification) {
        this.statement = statement;
        this.justification = justification;
    }
}
