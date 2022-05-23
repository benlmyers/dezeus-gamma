package com.benmyers.dezeus.core;

import java.util.HashSet;
import java.util.Set;

import com.benmyers.dezeus.App;
import com.benmyers.dezeus.core.error.DezeusException;
import com.benmyers.dezeus.core.error.ImproperPropositionException;
import com.benmyers.dezeus.lang.Symbol;

public class PropositionBuilder {

    private String input;

    public PropositionBuilder(String input) {
        this.input = input;
    }

    public Proposition build() throws DezeusException {
        String thereforeSymbol = App.symbols.get(Symbol.THEREFORE);
        thereforeSymbol = "\\" + thereforeSymbol;
        String[] split = input.split(thereforeSymbol);
        if (split.length != 2)
            throw new ImproperPropositionException();
        String premisesString = split[0];
        String conclusionString = split[1];
        split = null;
        String delimiter = App.symbols.get(Symbol.PREMISE_DELIMITER);
        delimiter = "\\" + delimiter;
        split = premisesString.split(delimiter);
        Set<Statement> premises = new HashSet<>();
        for (String item : split) {
            StatementBuilder builder = new StatementBuilder(item);
            Statement premise = builder.build();
            premises.add(premise);
        }
        StatementBuilder builder = new StatementBuilder(conclusionString);
        Statement conclusion = builder.build();
        return new Proposition(premises, conclusion);
    }
}
