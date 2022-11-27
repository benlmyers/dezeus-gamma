package com.benmyers.dezeus.core.derivation;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.StatementGroup;
import com.benmyers.dezeus.core.rule.Rule;

public class Arranger {

    StatementGroup knowns;
    Rule rule;

    public Arranger(StatementGroup knowns, Rule rule) {
        this.knowns = knowns;
        this.rule = rule;
    }

    public List<Statement> arrange() {
        // Return only the relevant knowns to the rule.
        Set<Class<? extends Statement>> inputClasses = rule.getInputClasses();
        List<Statement> relevantKnowns = knowns.stream()
                .filter(known -> inputClasses.contains(known.getClass()))
                .collect(Collectors.toList());
        return relevantKnowns;
    }
}
