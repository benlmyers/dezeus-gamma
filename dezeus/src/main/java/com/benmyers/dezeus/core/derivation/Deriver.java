package com.benmyers.dezeus.core.derivation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.benmyers.dezeus.core.Deduction;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.StatementGroup;
import com.benmyers.dezeus.core.error.ApplyMismatchException;
import com.benmyers.dezeus.core.error.InstantiateMismatchException;
import com.benmyers.dezeus.core.rule.Rule;
import com.benmyers.dezeus.core.util.Combinator;

public class Deriver {

    private StatementGroup knowns;
    private Rule rule;

    public Deriver(StatementGroup knowns, Rule rule) {
        this.knowns = knowns;
        this.rule = rule;
    }

    public Set<Deduction> deriveRelevant() throws InstantiateMismatchException, ApplyMismatchException {
        Arranger arranger = new Arranger(knowns, rule);
        List<Statement> arrangement = arranger.arrangeRelevant();
        Rule instantiatedRule = rule.instantiate(arrangement);
        Set<Deduction> deductions = instantiatedRule.apply(knowns);
        return deductions;
    }

    public Set<Deduction> deriveAny() {
        Set<Deduction> result = new HashSet<>();
        Arranger arranger = new Arranger(knowns, rule);
        List<StatementGroup> groups = arranger.arrangeAny();
        List<List<Statement>> combinations = Combinator.getCartesianProduct(groups);
        for (List<Statement> combination : combinations) {
            try {
                Deriver relevantDeriver = new Deriver(new StatementGroup(combination), rule);
                result.addAll(relevantDeriver.deriveRelevant());
            } catch (ApplyMismatchException | InstantiateMismatchException e) {
            }
        }
        return result;
    }
}
