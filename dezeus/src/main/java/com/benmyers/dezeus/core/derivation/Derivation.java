package com.benmyers.dezeus.core.derivation;

import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.justification.Justification;

public abstract class Derivation implements Justification {

    protected Statement statement;

    Derivation(Statement show) {
        this.statement = show;
    }

    public Statement getStatement() {
        return statement;
    }
}
