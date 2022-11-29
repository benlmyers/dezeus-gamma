package com.benmyers.dezeus.core.derivation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.benmyers.dezeus.core.Atom;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.StatementGroup;
import com.benmyers.dezeus.core.error.InstatiateMismatchException;
import com.benmyers.dezeus.core.rule.Rule;

public class Arranger {

    StatementGroup relevantKnowns;
    Rule rule;

    public Arranger(StatementGroup relevantKnowns, Rule rule) {
        this.relevantKnowns = relevantKnowns;
        this.rule = rule;
    }

    public List<Statement> arrange() {
        // If the size of relevantKnowns is larger than the size of rule's inputs, then
        // it is impossible
        // to arrange the knowns in a way that fits for instantiation.
        if (relevantKnowns.size() != rule.getInput().size()) {
            return new ArrayList<>();
        }
        // Convert the rule's input into an ordered list.
        List<Statement> ruleInputList = new ArrayList<>(rule.getInput());
        // Get a list of the rule's input types.
        List<Class<? extends Statement>> ruleInputTypes = new ArrayList<>();
        for (Statement statement : ruleInputList) {
            ruleInputTypes.add(statement.getClass());
        }
        // Get all permutations of the relevant knowns. One of these permutations should
        // match the rule's input.
        List<List<Statement>> possibleArrangements = relevantKnowns.getPermutations();

        // List<StatementGroup> toRemove = new ArrayList<>();
        // for (StatementGroup arrangement : possibleArrangements) {
        // List<Statement> arrangementList = new
        // ArrayList<>(arrangement.getStatementSet());
        // for (int i = 0; i < arrangement.size(); i++) {
        // if (arrangementList.get(i).getParameterizedClass() != ruleInputTypes.get(i))
        // {
        // toRemove.add(arrangement);
        // break;
        // }
        // }
        // }
        // possibleArrangements.removeAll(toRemove);

        // For each possible arrangement, check if it is valid.
        for (List<Statement> arrangement : possibleArrangements) {
            List<Statement> parameters = new ArrayList<>();
            // Get a list of parameters to instantiate the rule with.
            for (int i = 0; i < arrangement.size(); i++) {
                Statement statement = arrangement.get(i);
                // If the rule's i'th input is an Atom, then the i'th statement of the
                // arrangement is a parameter.
                // Otherwise, if the rule's i'th input parameterized type exactly matches that
                // of the i'th statement, add the
                // statement's parameters.
                if (ruleInputList.get(i) instanceof Atom) {
                    parameters.add(statement);
                } else {
                    parameters.addAll(statement.getParameters());
                }
            }
            // Remove any duplicates from the parameters.
            // Why: Consider the rule inputs [P->Q, Q->R], arrangement [A->B, B->C] with
            // (incorrect) parameter list [A, B, B, C].
            parameters = parameters.stream().distinct().collect(Collectors.toList());
            // Attempt to instantiate the rule using the above parameters.
            try {
                Rule instantiatedRule = rule.instantiate(parameters);
                // If the instantiated rule equals the relevant knowns, return the now correct
                // list of parameters.
                if (instantiatedRule.getInput().equals(relevantKnowns)) {
                    return parameters;
                }
            } catch (InstatiateMismatchException e) {
                System.out.println("UNEXPECTED ISSUE: Instantiation mismatch");
            }
        }
        return new ArrayList<>();
    }
}
