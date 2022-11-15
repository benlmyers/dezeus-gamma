package com.benmyers.dezeus.core.justification;

import com.benmyers.dezeus.core.rule.Rule;

public class RuleJustification implements Justification {

    private Rule rule;

    public RuleJustification(Rule rule) {
        this.rule = rule;
    }

    @Override
    public String getAbbr() {
        return "pr";
    }

    @Override
    public String getReason() {
        return "Premise provided";
    }
}