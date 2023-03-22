package com.benmyers.dezeus.core.rule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.benmyers.dezeus.core.Atom;
import com.benmyers.dezeus.core.Deduction;
import com.benmyers.dezeus.core.DeductionGroup;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.StatementGroup;
import com.benmyers.dezeus.core.error.ApplyMismatchException;
import com.benmyers.dezeus.core.error.InstantiateMismatchException;
import com.benmyers.dezeus.core.justification.RuleJustification;
import com.benmyers.dezeus.core.util.Copyable;

public abstract class Rule implements Copyable<Rule> {

    String name = "Unnamed Rule";
    int id = 0;
    boolean instantiated = false;

    StatementGroup input;
    StatementGroup output;

    public final void writeToFile() throws IOException {
        File file = new File("rules/" + id + ".rule");
        file.createNewFile();
        // Write the id, name, input, and output to the file
        String value = id + "\n" + name + "\n" + input + "\n" + output;
        Files.write(file.toPath(), value.getBytes());
    }

    public String getName() {
        return name;
    }

    public String getAbbr() {
        return name.replaceAll("\\B.|\\P{L}", "").toLowerCase();
    }

    @Override
    public String toString() {
        return "[" + id + "] " + input + " |- " + output;
    }

    public int getId() {
        return id;
    }

    protected Rule() {
    }

    protected Rule(String name, int id, StatementGroup input, StatementGroup output) {
        this.name = name;
        this.id = id;
        this.input = input;
        this.output = output;
    }

    public StatementGroup getInput() {
        return input;
    }

    public StatementGroup getOutput() {
        return output;
    }

    public List<Atom> getAtoms() {
        Set<Atom> atoms = new HashSet<>();
        for (Statement statement : input) {
            atoms.addAll(statement.getAtoms());
        }
        for (Statement statement : output) {
            atoms.addAll(statement.getAtoms());
        }
        List<Atom> atomList = new ArrayList<>();
        atomList.addAll(atoms);
        return atomList;
    }

    public Rule instantiate(List<Statement> arguments) throws InstantiateMismatchException {
        Rule rule = this.copy();
        rule.instantiated = true;
        List<Atom> atoms = getAtoms();
        if (arguments.size() != atoms.size()) {
            throw new InstantiateMismatchException();
        }
        Map<Atom, Statement> map = new HashMap<>();
        for (int i = 0; i < atoms.size(); i++) {
            map.put(atoms.get(i), arguments.get(i));
        }
        rule.input.setAtoms(map);
        rule.output.setAtoms(map);
        return rule;
    }

    public DeductionGroup apply(DeductionGroup input) throws ApplyMismatchException {
        StatementGroup _input = input.getStatements();
        if (!_input.equals(this.input)) {
            throw new ApplyMismatchException();
        }
        DeductionGroup deductions = new DeductionGroup();
        for (Statement statement : output) {
            deductions.add(new Deduction(statement, new RuleJustification(this, input)));
        }
        return deductions;
    }

    public Set<Class<? extends Statement>> getInputClasses() {
        Set<Class<? extends Statement>> classes = new HashSet<>();
        for (Statement statement : input) {
            classes.add(statement.getClass());
        }
        return classes;
    }

    public boolean canShow(Statement desiredResult) {
        for (Statement _output : output) {
            if (desiredResult.fits(_output)) {
                return true;
            }
        }
        return false;
    }
}