package com.benmyers.dezeus.core.rule;

import java.io.File;
import java.nio.file.Files;

import com.benmyers.dezeus.core.Proposition;
import com.benmyers.dezeus.core.StatementBuilder;
import com.benmyers.dezeus.core.StatementGroup;

public class Law extends Rule {

    public Law(File file) throws Exception {
        String[] lines = Files.readAllLines(file.toPath()).toArray(new String[0]);
        super.id = Integer.parseInt(lines[0]);
        super.name = lines[1];
        String input = lines[2];
        input = input.substring(1, input.length() - 1);
        String[] inputStatements = input.split(", ");
        StatementGroup group = new StatementGroup();
        for (String statement : inputStatements) {
            group.add(new StatementBuilder(statement).build());
        }
        super.input = group;
        super.output = new StatementGroup(new StatementBuilder(lines[3]).build());
    }

    public Law(int id, Proposition proposition) {
        super.id = id;
        super.name = "Unnamed Law";
        super.input = proposition.getPremises();
        super.output = new StatementGroup(proposition.getConclusion());
    }
}
