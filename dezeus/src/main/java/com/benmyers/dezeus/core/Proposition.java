package com.benmyers.dezeus.core;

import java.util.HashSet;
import java.util.Set;

import com.benmyers.dezeus.App;
import com.benmyers.dezeus.core.derivation.Prover;
import com.benmyers.dezeus.core.error.ProofNotFoundException;
import com.benmyers.dezeus.core.justification.PremiseJustification;
import com.benmyers.dezeus.core.rule.Theorem;
import com.benmyers.dezeus.lang.Symbol;

public class Proposition {

    private StatementGroup premises;
    private StatementGroup conclusion;

    public Proposition(StatementGroup premises, StatementGroup conclusion) {
        this.premises = premises;
        this.conclusion = conclusion;
    }

    @Override
    public String toString() {
        String premiseString = premises.toString();
        String conclusionString = conclusion.toString();
        return premiseString + " " + App.symbols.get(Symbol.THEREFORE) + " " + conclusionString;
    }

    public StatementGroup getPremises() {
        return premises;
    }

    public Set<Deduction> getPremisesAsDeductions() {
        Set<Deduction> set = new HashSet<>();
        for (Statement statement : premises) {
            set.add(new Deduction(statement, new PremiseJustification()));
        }
        return set;
    }

    public Statement getConclusion() {
        return conclusion;
    }

    public Theorem prove() throws ProofNotFoundException {
        Prover prover = new Prover(this);
        return new Theorem(this, prover.prove());
    }
}
