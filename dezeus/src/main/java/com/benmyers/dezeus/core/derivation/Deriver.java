package com.benmyers.dezeus.core.derivation;

import java.util.List;

import com.benmyers.dezeus.core.Deduction;
import com.benmyers.dezeus.core.DeductionGroup;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.error.ApplyMismatchException;
import com.benmyers.dezeus.core.error.InstantiateMismatchException;
import com.benmyers.dezeus.core.rule.Rule;
import com.benmyers.dezeus.core.util.Combinator;

public class Deriver {

    private DeductionGroup knowns;
    private Rule rule;

    public Deriver(DeductionGroup knowns, Rule rule) {
        this.knowns = knowns;
        this.rule = rule;
    }

    public DeductionGroup deriveRelevant() throws InstantiateMismatchException, ApplyMismatchException {
        Arranger arranger = new Arranger(knowns, rule);
        List<Statement> arrangement = arranger.arrangeRelevant();
        Rule instantiatedRule = rule.instantiate(arrangement);
        DeductionGroup deductions = instantiatedRule.apply(knowns);
        return deductions;
    }

    public DeductionGroup deriveAny() {
        DeductionGroup result = new DeductionGroup();
        Arranger arranger = new Arranger(knowns, rule);
        List<DeductionGroup> groups = arranger.arrangeAny();
        List<List<Deduction>> combinations = Combinator.getCartesianProduct(groups);
        for (List<Deduction> combination : combinations) {
            try {
                Deriver relevantDeriver = new Deriver(new DeductionGroup(combination), rule);
                result.addAll(relevantDeriver.deriveRelevant());
            } catch (ApplyMismatchException | InstantiateMismatchException e) {
            }
        }
        return result;
    }
}
