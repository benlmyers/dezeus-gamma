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

    StatementGroup knowns;
    Rule rule;

    public Arranger(StatementGroup knowns, Rule rule) {
        this.knowns = knowns;
        this.rule = rule;
    }

    /**
     * Arranges any supplied knowns for use in a rule.
     * Consider knowns = {~A, A->B, BvC}. Then for rule = P. P->Q |- Q, arrangeAny()
     * returns [{~A, A->B, BvC}, {A->B}].
     * 
     * @return A list of StatementGroups. The ith element of the list are the
     *         possible statements that apply to the ith input of the rule.
     */
    public List<StatementGroup> arrangeAny() {
        return null;
    }

    /**
     * Arranges only relevant knowns for use in a rule.
     * Consider knowns = {(A->B)->C, A->B}. Then for rule = P. P->Q |- Q,
     * arrangeRelevant() returns [A->B, (A->B)->C].
     * returns [{~A, A->B, BvC}, {A->B}].
     * 
     * @return A list of Statements. The statements are ordered to be used in the
     *         supplied rule.
     */
    public List<Statement> arrangeRelevant() {
        // If the size of relevant knowns is larger than the size of rule's inputs, then
        // it is impossible
        // to arrange the knowns in a way that fits for instantiation.
        if (knowns.size() != rule.getInput().size()) {
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
        List<List<Statement>> possibleArrangements = knowns.getPermutations();
        // For each possible arrangement, check if it is valid.
        for (List<Statement> arrangement : possibleArrangements) {
            List<Statement> parameters = new ArrayList<>();
            // Get a list of parameters to instantiate the rule with.
            for (int i = 0; i < arrangement.size(); i++) {
                Statement statement = arrangement.get(i);
                // If the rule's i'th input is an Atom, then the i'th statement of the
                // arrangement is a parameter.
                // Otherwise, add the statement's parameters.
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
                if (instantiatedRule.getInput().equals(knowns)) {
                    return parameters;
                }
            } catch (InstatiateMismatchException e) {
                System.out.println("UNEXPECTED ISSUE: Instantiation mismatch");
            }
        }
        return new ArrayList<>();
    }
}
