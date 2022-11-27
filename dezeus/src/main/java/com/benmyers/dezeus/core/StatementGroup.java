package com.benmyers.dezeus.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StatementGroup extends Statement implements Collection<Statement> {

    private Set<Statement> statements = new HashSet<>();

    public StatementGroup() {
    }

    public StatementGroup(Statement a) {
        this();
        add(a);
    }

    public StatementGroup(Statement a, Statement b) {
        this(a);
        add(b);
    }

    public StatementGroup(Statement a, Statement b, Statement c) {
        this(a, b);
        add(c);
    }

    public StatementGroup(Statement a, Statement b, Statement c, Statement d) {
        this(a, b, c);
        add(d);
    }

    // Implmentation

    @Override
    public int size() {
        return statements.size();
    }

    @Override
    public boolean isEmpty() {
        return statements.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return statements.contains(o);
    }

    @Override
    public Iterator<Statement> iterator() {
        return statements.iterator();
    }

    @Override
    public Object[] toArray() {
        return statements.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return statements.toArray(a);
    }

    @Override
    public boolean add(Statement e) {
        return statements.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return statements.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return statements.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Statement> c) {
        return statements.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return statements.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return statements.retainAll(c);
    }

    @Override
    public void clear() {
        statements.clear();
    }

    @Override
    public String toString() {
        if (size() == 0)
            return "";
        String string = "";
        for (Statement statement : statements) {
            string += statement + ", ";
        }
        return string.substring(0, string.length() - 2);
    }

    // Utility

    public Statement toStatement() {
        if (size() == 0)
            return null;
        Statement[] arr = (Statement[]) toArray();
        Statement statement = arr[0];
        if (size() == 1)
            return statement;
        for (int i = 1; i < arr.length; i++) {
            statement = statement.and(arr[i]);
        }
        return statement;
    }

    @Override
    public String toEnglish() {
        if (size() == 0)
            return "";
        String string = "";
        for (Statement statement : statements) {
            string += statement.toEnglish() + ", ";
        }
        return string.substring(0, string.length() - 2);
    }

    @Override
    public String toLaTeX() {
        if (size() == 0)
            return "";
        String string = "";
        for (Statement statement : statements) {
            string += statement.toLaTeX() + ", ";
        }
        return string.substring(0, string.length() - 2);
    }

    @Override
    public List<Atom> getAtoms() {
        List<Atom> atoms = new ArrayList<>();
        for (Statement statement : statements) {
            atoms.addAll(statement.getAtoms());
        }
        return atoms;
    }

    @Override
    public void setAtoms(Map<Atom, Statement> map) {
        Set<Statement> newStatements = new HashSet<>();
        for (Statement statement : statements) {
            if (statement instanceof Atom) {
                newStatements.add(map.get(statement));
            } else {
                statement.setAtoms(map);
                newStatements.add(statement);
            }
        }
        this.statements = newStatements;
    }
}
