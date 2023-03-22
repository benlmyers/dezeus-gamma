package com.benmyers.dezeus.core.derivation;

import java.util.Set;

import com.benmyers.dezeus.core.DeductionGroup;
import com.benmyers.dezeus.core.rule.Rule;
import com.benmyers.dezeus.core.rule.RulesManager;

public class Deducer {

    DeductionGroup knowns;

    public Deducer(DeductionGroup knowns) {
        this.knowns = knowns;
    }

    public DeductionGroup getDeductions() {
        DeductionGroup result = new DeductionGroup();
        Set<Rule> rules = new RulesManager().getAll();
        for (Rule rule : rules) {
            Deriver deriver = new Deriver(knowns, rule);
            DeductionGroup deductions = deriver.deriveAny();
            result.addAll(deductions);
        }
        return result;
    }
}
