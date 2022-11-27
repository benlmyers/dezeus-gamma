package com.benmyers.dezeus.core.derivation;

import java.util.Set;
import java.util.stream.Collectors;

import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.StatementGroup;
import com.benmyers.dezeus.core.rule.Rule;
import com.benmyers.dezeus.core.rule.RulesManager;

public class Deducer {

    StatementGroup knowns;

    public Deducer(StatementGroup knowns) {
        this.knowns = knowns;
    }

    private Set<Class<? extends Statement>> getKnownClasses() {
        Set<Class<? extends Statement>> knownClasses = knowns.stream()
                .map(Statement::getClass)
                .collect(Collectors.toSet());
        return knownClasses;
    }

    public Set<Rule> getRelevantRules() {
        Set<Class<? extends Statement>> knownClasses = getKnownClasses();
        Set<Rule> relevantRules = new RulesManager().getAll().stream()
                .filter(rule -> knownClasses.containsAll(rule.getInputClasses()))
                .collect(Collectors.toSet());
        return relevantRules;
    }
}
