package com.benmyers.dezeus.core.derivation;

import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.justification.Justification;

public abstract class Derivation implements Justification {

    Statement statement;

    public abstract String toString();

    Derivation(Statement show) {
        this.statement = show;
    }
}
