package com.benmyers.dezeus.core.rule;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import com.benmyers.dezeus.core.error.RuleIndexingException;
import com.benmyers.dezeus.core.error.RuleNotFoundException;

public class RulesManager {

    private Set<Rule> rules = new HashSet<>();

    public RulesManager() {
        loadAll();
    }

    public void updateIndex() throws RuleIndexingException {
        // TODO
    }

    private void loadAll() {
        // TODO
    }

    public Set<Rule> getAll() {
        return rules;
    }

    public Rule get(int id) throws RuleNotFoundException {
        // TODO
        return null;
    }

    public void listAll() {
        rules.forEach(System.out::println);
    }

    private Rule getRule(File file) throws Exception {
        // TODO
        return null;
    }
}
