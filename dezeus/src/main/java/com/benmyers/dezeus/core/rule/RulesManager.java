package com.benmyers.dezeus.core.rule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import com.benmyers.dezeus.core.error.RuleNotFoundException;

public class RulesManager {

    private Set<Rule> rules = new HashSet<>();

    public RulesManager() {
        loadAll();
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

    public Rule getRule(File file) throws Exception {
        // TODO
        return null;
    }
}
