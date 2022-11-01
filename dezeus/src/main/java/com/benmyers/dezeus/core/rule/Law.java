package com.benmyers.dezeus.core.rule;

import java.io.File;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

import com.benmyers.dezeus.core.Proposition;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.StatementBuilder;

public class Law extends Rule {

    public Law(File file) throws Exception {
        String[] lines = Files.readAllLines(file.toPath()).toArray(new String[0]);
        super.id = Integer.parseInt(lines[0]);
        super.name = lines[1];
        Set<Statement> set = new HashSet<>();
        set.add(new StatementBuilder(lines[2]).build());
        super.input = set;
        super.output = new StatementBuilder(lines[3]).build();
    }

    public Law(int id, Proposition proposition) {
        super.id = id;
        super.name = "Unnamed Law";
        super.input = proposition.getPremises();
        super.output = proposition.getConclusion();
    }
}
