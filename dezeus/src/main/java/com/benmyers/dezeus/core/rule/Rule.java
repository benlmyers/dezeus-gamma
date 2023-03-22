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
import com.benmyers.dezeus.core.Proposition;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.StatementGroup;
import com.benmyers.dezeus.core.error.ApplyMismatchException;
import com.benmyers.dezeus.core.error.InstantiateMismatchException;
import com.benmyers.dezeus.core.justification.RuleJustification;
import com.benmyers.dezeus.core.util.Copyable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Rule implements Copyable<Rule> {

    String id = "empty";
    String name = "Empty";
    String code = "UNDEF";
    transient boolean instantiated = false;
    Label label = Label.UNLABELLED;

    Proposition proposition = new Proposition(new StatementGroup(), new StatementGroup());

    public final void writeToFile() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this);
        System.out.println(json);
        File file = new File("rules/" + f(label) + "/" + id + ".json");
        file.getParentFile().mkdirs();
        file.createNewFile();
        Files.write(file.toPath(), json.getBytes());
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + proposition.getPremises() + " |- " + proposition.getConclusions();
    }

    public String getId() {
        return id;
    }

    public Rule(String name, String code, StatementGroup input, StatementGroup output) {
        this.id = name;
        this.name = name;
        this.code = code;
        this.proposition = new Proposition(input, output);
    }

    public Rule(String name, String code, Proposition proposition) {
        this.id = name;
        this.name = name;
        this.code = code;
        this.proposition = proposition;
    }

    public StatementGroup getInput() {
        return proposition.getPremises();
    }

    public StatementGroup getOutput() {
        return proposition.getConclusions();
    }

    public List<Atom> getAtoms() {
        Set<Atom> atoms = new HashSet<>();
        for (Statement statement : proposition.getPremises()) {
            atoms.addAll(statement.getAtoms());
        }
        for (Statement statement : proposition.getConclusions()) {
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
        rule.proposition.getPremises().setAtoms(map);
        rule.proposition.getConclusions().setAtoms(map);
        return rule;
    }

    public DeductionGroup apply(DeductionGroup input) throws ApplyMismatchException {
        StatementGroup _input = input.getStatements();
        if (!_input.equals(this.proposition.getPremises())) {
            throw new ApplyMismatchException();
        }
        DeductionGroup deductions = new DeductionGroup();
        for (Statement statement : proposition.getConclusions()) {
            deductions.add(new Deduction(statement, new RuleJustification(this, input)));
        }
        return deductions;
    }

    public Set<Class<? extends Statement>> getInputClasses() {
        Set<Class<? extends Statement>> classes = new HashSet<>();
        for (Statement statement : proposition.getPremises()) {
            classes.add(statement.getClass());
        }
        return classes;
    }

    public boolean canShow(Statement desiredResult) {
        for (Statement _output : proposition.getConclusions()) {
            if (desiredResult.fits(_output)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Rule copy() {
        return new Rule(name, code, getInput(), getOutput());
    }

    public enum Label {
        UNLABELLED, AXIOM, THEOREM, HYPOTHESIS, DEFINITION, LEMMA, COROLLARY, PROPOSITION, POSTULATE, EXERCISE
    }

    private String f(Label l) {
        switch (l) {
            case UNLABELLED:
                return "unlabelled";
            case AXIOM:
                return "axiom";
            case THEOREM:
                return "theorem";
            case HYPOTHESIS:
                return "hypothesis";
            case DEFINITION:
                return "definition";
            case LEMMA:
                return "lemma";
            case COROLLARY:
                return "corollary";
            case PROPOSITION:
                return "proposition";
            case POSTULATE:
                return "postulate";
            case EXERCISE:
                return "exercise";
            default:
                return "unlabelled";
        }
    }
}