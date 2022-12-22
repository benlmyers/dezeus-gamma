package com.benmyers.dezeus.core.derivation;

import java.util.HashSet;
import java.util.Set;

import com.benmyers.dezeus.core.Deduction;
import com.benmyers.dezeus.core.StatementGroup;
import com.benmyers.dezeus.core.rule.Rule;
import com.benmyers.dezeus.core.rule.RulesManager;

public class Deducer {

    StatementGroup knowns;

    public Deducer(StatementGroup knowns) {
        this.knowns = knowns;
    }

    public Set<Deduction> getDeductions() {
        Set<Deduction> result = new HashSet<>();
        Set<Rule> rules = new RulesManager().getAll();
        for (Rule rule : rules) {
            Deriver deriver = new Deriver(knowns, rule);
            Set<Deduction> deductions = deriver.deriveAny();
            result.addAll(deductions);
        }
        return result;
    }
}
