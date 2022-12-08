package com.benmyers.dezeus.core.derivation;

import java.util.List;
import java.util.Set;

import com.benmyers.dezeus.core.Deduction;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.StatementGroup;
import com.benmyers.dezeus.core.error.ApplyMismatchException;
import com.benmyers.dezeus.core.error.InstatiateMismatchException;
import com.benmyers.dezeus.core.rule.Rule;

public class Deriver {

    private StatementGroup relevantKnowns;
    private Rule rule;

    public Deriver(StatementGroup relevantKnowns, Rule rule) {
        this.relevantKnowns = relevantKnowns;
        this.rule = rule;
    }

    public Set<Deduction> derive() throws InstatiateMismatchException, ApplyMismatchException {
        Arranger arranger = new Arranger(relevantKnowns, rule);
        List<Statement> arrangement = arranger.arrangeRelevant();
        Rule instantiatedRule = rule.instantiate(arrangement);
        Set<Deduction> deductions = instantiatedRule.apply(relevantKnowns);
        return deductions;
    }
}
