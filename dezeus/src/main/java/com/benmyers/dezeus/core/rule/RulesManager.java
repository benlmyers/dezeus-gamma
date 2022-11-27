package com.benmyers.dezeus.core.rule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class RulesManager {

    private Set<Rule> rules = new HashSet<>();

    public RulesManager() {
        loadAll();
    }

    private void loadAll() {
        try (Stream<Path> paths = Files.walk(Paths.get("./rules"))) {
            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath) && filePath.toString().endsWith(".rule")) {
                    try {
                        rules.add(new Law(filePath.toFile()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<Rule> getAll() {
        return rules;
    }

    public Rule get(int id) {
        for (Rule rule : rules) {
            if (rule.getId() == id) {
                return rule;
            }
        }
        return null;
    }

    public void listAll() {
        rules.forEach(System.out::println);
    }

    public Rule getRule(File file) throws Exception {
        return new Law(file);
    }
}
