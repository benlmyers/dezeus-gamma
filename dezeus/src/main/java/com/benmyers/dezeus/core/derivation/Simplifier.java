package com.benmyers.dezeus.core.derivation;

import java.util.Set;

import com.benmyers.dezeus.core.Statement;

public class Simplifier {

    Set<Statement> derivations;

    public Simplifier(Set<Statement> derivations) {
        this.derivations = derivations;
    }
}
