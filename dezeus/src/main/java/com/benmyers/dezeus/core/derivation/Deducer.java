package com.benmyers.dezeus.core.derivation;

import java.util.HashSet;
import java.util.Set;

import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.StatementGroup;
import com.benmyers.dezeus.core.rule.Rule;
import com.benmyers.dezeus.core.rule.RulesManager;

public class Deducer {

    StatementGroup knowns;

    public Deducer(StatementGroup knowns) {
        this.knowns = knowns;
    }

    public Set<Rule> getRelevantRules(Statement desiredResult) {
        Set<Rule> result = new HashSet<>();
        Set<Rule> allRules = new RulesManager().getAll();
        for (Rule rule : allRules) {
            if (rule.canShow(desiredResult)) {
                result.add(rule);
            }
        }
        return result;
    }
}
