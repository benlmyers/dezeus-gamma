package com.benmyers.dezeus.core.justification;

import com.benmyers.dezeus.core.DeductionGroup;
import com.benmyers.dezeus.core.rule.Rule;

public class RuleJustification implements Justification {

    private Rule rule;
    private DeductionGroup evidence;

    public RuleJustification(Rule rule, DeductionGroup evidence) {
        this.rule = rule;
        this.evidence = evidence;
    }

    public DeductionGroup getEvidence() {
        return evidence;
    }

    @Override
    public String getAbbr() {
        return rule.getAbbr();
    }

    @Override
    public String getReason() {
        return rule.getName();
    }
}
