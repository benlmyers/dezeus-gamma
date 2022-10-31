package com.benmyers.dezeus.core.derivation;

import java.util.Set;

import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.rule.Rule;

public class Arranger {

    Set<Statement> knowns;
    Rule rule;

    public Arranger(Set<Statement> knowns, Rule rule) {
        this.knowns = knowns;
        this.rule = rule;
    }
}
