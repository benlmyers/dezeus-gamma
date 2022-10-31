package com.benmyers.dezeus.core;

import java.util.Set;

import com.benmyers.dezeus.App;
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
}
