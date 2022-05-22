package com.benmyers.dezeus.core;

import java.util.HashSet;
import java.util.Set;

import com.benmyers.dezeus.core.derivation.Derivation;
import com.benmyers.dezeus.core.invalidity.Invalidity;
import com.benmyers.dezeus.core.justification.PremiseJustification;

public class Proposition {

    private Set<Statement> premises;
    private Statement conclusion;

    public Proposition(Set<Statement> premises, Statement conclusion) {
        this.premises = premises;
        this.conclusion = conclusion;
    }

    public Derivation prove() throws Invalidity {
        Set<Deduction> deductions = new HashSet<>();
        for (Statement statement : premises) {
            Deduction deduction = new Deduction(statement, new PremiseJustification());
            deductions.add(deduction);
        }
        return conclusion.show(deductions);
    }
}
