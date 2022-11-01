package com.benmyers.dezeus.core;

import java.util.HashSet;
import java.util.Set;

import com.benmyers.dezeus.App;
import com.benmyers.dezeus.core.justification.PremiseJustification;
import com.benmyers.dezeus.lang.Symbol;
import com.benmyers.dezeus.util.StatementUtil;

public class Proposition {

    private Set<Statement> premises;
    private Statement conclusion;

    public Proposition(Set<Statement> premises, Statement conclusion) {
        this.premises = premises;
        this.conclusion = conclusion;
    }

    @Override
    public String toString() {
        String premiseString = StatementUtil.setToString(premises);
        String conclusionString = conclusion.toString();
        return premiseString + " " + App.symbols.get(Symbol.THEREFORE) + " " + conclusionString;
    }

    public Set<Statement> getPremises() {
        return premises;
    }

    public Set<Deduction> getPremisesAsDeductions() {
        Set<Deduction> deductions = new HashSet<>();
        for (Statement premise : premises) {
            deductions.add(new Deduction(premise, new PremiseJustification()));
        }
        return deductions;
    }

    public Statement getConclusion() {
        return conclusion;
    }
}
