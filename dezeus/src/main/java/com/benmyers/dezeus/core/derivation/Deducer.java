package com.benmyers.dezeus.core.derivation;

import java.util.Set;

import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.rule.Rule;

public class Deducer {

    Set<Statement> knowns;

    public Deducer(Set<Statement> knowns) {
        this.knowns = knowns;
    }

    public Rule findRule() {
        return null;
    }
}