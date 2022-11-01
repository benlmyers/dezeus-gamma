package com.benmyers.dezeus.core.rule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RulesManager {

    String directory;

    public RulesManager(String directory) {
        this.directory = directory;
    }

    public List<Rule> getAll() {
        ArrayList<Rule> rules = new ArrayList<>();
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
        return rules;
    }

    public void listAll() {
        getAll().forEach(System.out::println);
    }

    public Rule getRule(File file) throws Exception {
        return new Law(file);
    }
}
