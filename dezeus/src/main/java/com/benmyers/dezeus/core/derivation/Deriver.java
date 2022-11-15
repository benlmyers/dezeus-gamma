package com.benmyers.dezeus.core.derivation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.benmyers.dezeus.core.Atom;
import com.benmyers.dezeus.core.Deduction;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.StatementGroup;
import com.benmyers.dezeus.core.rule.Rule;

public class Deriver {

    private List<Statement> relevantKnowns;
    private Rule rule;

    public Deriver(StatementGroup relevantKnowns, Rule rule) {
        this.relevantKnowns = new ArrayList<>(relevantKnowns);
        this.rule = rule;
    }

    public Deduction derive() {
        List<Statement> input = new ArrayList<>(rule.getInput());
        Map<Atom, Statement> map = new HashMap<>();
        for (int i = 0; i < input.size(); i++) {
            List<Atom> atoms = input.get(i).getAtoms();
            for (Atom atom : atoms) {
                map.put(atom, relevantKnowns.get(i));
            }
        }
        return null;
    }
}
