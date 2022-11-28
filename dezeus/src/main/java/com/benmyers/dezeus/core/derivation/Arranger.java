package com.benmyers.dezeus.core.derivation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.benmyers.dezeus.core.Atom;
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

    // TODO: Implement
    // public Map<Atom, Statement> arrange(StatementGroup relevantStatements, Rule
    // rule) {
    // List<Statement> ruleInput = new ArrayList<>(rule.getInput());
    // List<Class<? extends Statement>> ruleInputTypes = new ArrayList<>();
    // for (Statement statement : ruleInput) {
    // ruleInputTypes.add(statement.get());
    // }
    // List<StatementGroup> possibleArrangements =
    // relevantStatements.getPermutations();
    // }
}
