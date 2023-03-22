package com.benmyers.dezeus.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DeductionGroup implements Collection<Deduction> {

    private Set<Deduction> deductions = new HashSet<>();

    public DeductionGroup() {
    }

    public DeductionGroup(Deduction a) {
        this();
        add(a);
    }

    public DeductionGroup(Deduction a, Deduction b) {
        this(a);
        add(b);
    }

    public DeductionGroup(Deduction a, Deduction b, Deduction c) {
        this(a, b);
        add(c);
    }

    public DeductionGroup(Deduction a, Deduction b, Deduction c, Deduction d) {
        this(a, b, c);
        add(d);
    }

    public DeductionGroup(List<Deduction> l) {
        this();
        deductions = new HashSet<>(l);
    }

    // Implmentation

    @Override
    public int size() {
        return deductions.size();
    }

    @Override
    public boolean isEmpty() {
        return deductions.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return deductions.contains(o);
    }

    @Override
    public Iterator<Deduction> iterator() {
        return deductions.iterator();
    }

    @Override
    public Object[] toArray() {
        return deductions.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return deductions.toArray(a);
    }

    @Override
    public boolean add(Deduction e) {
        return deductions.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return deductions.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return deductions.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Deduction> c) {
        return deductions.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return deductions.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return deductions.retainAll(c);
    }

    @Override
    public void clear() {
        deductions.clear();
    }

    @Override
    public String toString() {
        if (size() == 0)
            return "";
        String string = "";
        for (Deduction Deduction : deductions) {
            string += Deduction + ", ";
        }
        return string.substring(0, string.length() - 2);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeductionGroup) {
            DeductionGroup group = (DeductionGroup) obj;
            return group.getDeductions().equals(deductions);
        }
        return false;
    }

    // Utility

    public Set<Deduction> getDeductions() {
        return deductions;
    }

    public StatementGroup getStatements() {
        StatementGroup statements = new StatementGroup();
        for (Deduction deduction : deductions) {
            statements.add(deduction.getStatement());
        }
        return statements;
    }

    public List<List<Deduction>> getPermutations() {
        List<List<Deduction>> permutations = new ArrayList<>();
        if (size() == 0)
            return permutations;
        if (size() == 1) {
            permutations.add(new ArrayList<>(this));
            return permutations;
        }
        for (Deduction deduction : deductions) {
            DeductionGroup group = new DeductionGroup();
            group.addAll(deductions);
            group.remove(deduction);
            List<List<Deduction>> subPermutations = group.getPermutations();
            for (List<Deduction> subGroup : subPermutations) {
                subGroup.add(deduction);
                permutations.add(subGroup);
            }
        }
        return permutations;
    }
}
