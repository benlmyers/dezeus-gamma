package com.benmyers.dezeus.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.benmyers.dezeus.core.error.DezeusException;

public class Atom extends Statement {

    private String name;
    private String latex;

    public Atom(String name) throws DezeusException {
        this(name, name, false);
    }

    public Atom(String name, String latex) throws DezeusException {
        this(name, latex, false);
    }

    Atom(String name, Boolean bypass) throws DezeusException {
        this(name, name, bypass);
    }

    Atom(String name, String latex, Boolean bypass) throws DezeusException {
        if (!bypass)
            Namespace.registerName(name);
        this.name = name;
        this.latex = latex;
    }

    @Override
    public int complexity() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Atom) {
            Atom atom = (Atom) obj;
            return atom.name.equals(name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public List<Atom> getAtoms() {
        List<Atom> atoms = new ArrayList<>();
        atoms.add(this);
        return atoms;
    }

    @Override
    public void setAtoms(Map<Atom, Statement> map) {
        Atom newAtom = (Atom) map.get(this);
        name = newAtom.name;
        latex = newAtom.latex;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String toEnglish() {
        return name;
    }

    @Override
    public String toLaTeX() {
        return latex;
    }

    public String getName() {
        return name;
    }

    public Atom copy() {
        try {
            return new Atom(name, latex, true);
        } catch (DezeusException e) {
            return null;
        }
    }

    @Override
    public List<Statement> getParameters() {
        List<Statement> list = new ArrayList<>();
        list.add(this);
        return list;
    }

    @Override
    public ParameterizedClass<? extends Statement> getParameterizedClass() {
        return new ParameterizedClass<>(Atom.class, new ArrayList<>());
    }
}