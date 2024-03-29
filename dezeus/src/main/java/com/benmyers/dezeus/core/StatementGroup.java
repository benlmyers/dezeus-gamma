package com.benmyers.dezeus.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.benmyers.dezeus.core.justification.DebugJustification;

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

    public StatementGroup(List<Statement> l) {
        this();
        statements = new HashSet<>(l);
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StatementGroup) {
            StatementGroup group = (StatementGroup) obj;
            return group.getStatementSet().equals(statements);
        }
        return false;
    }

    public StatementGroup copy() {
        StatementGroup copy = new StatementGroup();
        for (Statement s : statements) {
            copy.add(s.copy());
        }
        return copy;
    }

    // Utility

    public Set<Statement> getStatementSet() {
        return statements;
    }

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

    public DeductionGroup toDeductionsDebug() {
        DeductionGroup deductions = new DeductionGroup();
        for (Statement statement : statements) {
            deductions.add(new Deduction(statement, new DebugJustification()));
        }
        return deductions;
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

    public List<List<Statement>> getPermutations() {
        List<List<Statement>> permutations = new ArrayList<>();
        if (size() == 0)
            return permutations;
        if (size() == 1) {
            permutations.add(new ArrayList<>(this));
            return permutations;
        }
        for (Statement statement : statements) {
            StatementGroup group = new StatementGroup();
            group.addAll(statements);
            group.remove(statement);
            List<List<Statement>> subPermutations = group.getPermutations();
            for (List<Statement> subGroup : subPermutations) {
                subGroup.add(statement);
                permutations.add(subGroup);
            }
        }
        return permutations;
    }

    @Override
    public List<Statement> getParameters() {
        List<Statement> list = new ArrayList<>();
        for (Statement statement : statements) {
            list.add(statement);
        }
        return list;
    }

    @Override
    public ParameterizedClass<? extends Statement> getParameterizedClass() {
        List<ParameterizedClass<?>> list = new ArrayList<>();
        for (Statement s : statements) {
            list.add(s.getParameterizedClass());
        }
        return new ParameterizedClass<>(StatementGroup.class, list);
    }

    @Override
    public boolean fits(Statement template, Map<Atom, Statement> map) {
        if (template instanceof Atom) {
            Atom atom = (Atom) template;
            if (map.get(atom) != null && map.get(atom) != this) {
                return false;
            }
            map.put(atom, this);
            return true;
        } else if (template instanceof StatementGroup) {
            List<Statement> parameters = getParameters();
            List<Statement> templateParameters = template.getParameters();
            if (parameters.size() > templateParameters.size()) {
                return false;
            }
            for (int i = 0; i < parameters.size(); i++) {
                Statement parameter = parameters.get(i);
                boolean fits = false;
                for (int j = 0; j < templateParameters.size(); j++) {
                    Statement templateParameter = templateParameters.get(i);
                    if (parameter.fits(templateParameter, map)) {
                        fits = true;
                    }
                    if (!fits)
                        return false;
                }
            }
            return true;
        }
        return false;
    }
}
