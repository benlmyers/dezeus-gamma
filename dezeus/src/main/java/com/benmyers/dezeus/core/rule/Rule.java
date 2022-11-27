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
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.StatementGroup;
import com.benmyers.dezeus.core.error.ApplyMismatchException;
import com.benmyers.dezeus.core.error.InstatiateMismatchException;
import com.benmyers.dezeus.core.justification.RuleJustification;

public abstract class Rule implements Cloneable {

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

    @Override
    public String toString() {
        return "[" + id + "] " + input + " |- " + output;
    }

    public int getId() {
        return id;
    }

    public StatementGroup getInput() {
        return input;
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

    public Rule instantiate(List<Statement> arguments) throws InstatiateMismatchException {
        try {
            Rule rule = (Rule) this.clone();
            List<Atom> atoms = getAtoms();
            if (arguments.size() != atoms.size()) {
                throw new InstatiateMismatchException();
            }
            Map<Atom, Statement> map = new HashMap<>();
            for (int i = 0; i < atoms.size(); i++) {
                map.put(atoms.get(i), arguments.get(i));
            }
            rule.input.setAtoms(map);
            rule.output.setAtoms(map);
            return rule;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Set<Deduction> apply(StatementGroup input, boolean allowSubset) throws ApplyMismatchException {
        if (allowSubset && !this.input.containsAll(input)) {
            throw new ApplyMismatchException();
        }
        if (!allowSubset && !this.input.equals(input)) {
            throw new ApplyMismatchException();
        }
        Set<Deduction> deductions = new HashSet<>();
        for (Statement statement : output) {
            deductions.add(new Deduction(statement, new RuleJustification(this)));
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
}