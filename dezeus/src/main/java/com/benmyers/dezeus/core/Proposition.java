package com.benmyers.dezeus.core;

import java.util.HashSet;
import java.util.Set;

import com.benmyers.dezeus.core.invalidity.Invalidity;

public class Proposition {

    private Statements premises;
    private Statement conclusion;

    public Proposition(Statements premises, Statement conclusion) {
        this.premises = premises;
        this.conclusion = conclusion;
    }

    public Derivation prove() throws Invalidity {
        Set<Premise> _premises = new HashSet<>();
        for (Statement statement : premises) {
            _premises.add(new Premise(statement));
        }
        Show showConc = new Show(conclusion, _premises);
        Derivation derivation = showConc.show();
        return derivation;
    }
}
