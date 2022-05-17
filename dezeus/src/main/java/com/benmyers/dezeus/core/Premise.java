package com.benmyers.dezeus.core;

import com.benmyers.dezeus.core.justification.PremiseJustification;

public class Premise extends Deduction {

    public Premise(Statement statement) {
        super(statement, new PremiseJustification());
    }
}
