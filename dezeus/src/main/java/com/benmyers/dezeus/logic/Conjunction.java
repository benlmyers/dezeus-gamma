package com.benmyers.dezeus.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.benmyers.dezeus.App;
import com.benmyers.dezeus.core.Atom;
import com.benmyers.dezeus.core.DeductionGroup;
import com.benmyers.dezeus.core.Operator;
import com.benmyers.dezeus.core.ParameterizedClass;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.StatementGroup;
import com.benmyers.dezeus.core.derivation.Show;
import com.benmyers.dezeus.lang.Symbol;

public class Conjunction extends Operator {

    private Statement a, b;

    public Conjunction(Statement a, Statement b) {
        this.a = a;
        this.b = b;
    }

    public static int getOrder() {
        return 2;
    }

    public static Symbol getSymbol() {
        return Symbol.AND;
    }

    public Statement getLeft() {
        return a;
    }

    public Statement getRight() {
        return b;
    }

    public StatementGroup asGroup() {
        return new StatementGroup(this);
    }

    public Conjunction copy() {
        return new Conjunction(a.copy(), b.copy());
    }

    @Override
    public Show show(DeductionGroup deductions) {
        return Show.direct(deductions, this.asGroup());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Conjunction) {
            Conjunction statement = (Conjunction) obj;
            return statement.getLeft().equals(a) && statement.getRight().equals(b);
        }
        return false;
    }

    @Override
    public List<Atom> getAtoms() {
        List<Atom> atoms = new ArrayList<>();
        atoms.addAll(a.getAtoms());
        atoms.addAll(b.getAtoms());
        return atoms;
    }

    @Override
    public String toString() {
        return wrapString(a) + App.symbols.get(Symbol.AND) + wrapString(b);
    }

    @Override
    public String toEnglish() {
        return wrapEngligh(a) + " and " + wrapEngligh(b);
    }

    @Override
    public String toLaTeX() {
        return wrapLaTeX(a) + " \\land " + wrapLaTeX(b);
    }

    @Override
    public void setAtoms(Map<Atom, Statement> map) {
        if (a instanceof Atom) {
            a = map.get(a);
        } else {
            a.setAtoms(map);
        }
        if (b instanceof Atom) {
            b = map.get(b);
        } else {
            b.setAtoms(map);
        }
    }

    @Override
    public List<Statement> getParameters() {
        List<Statement> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        return list;
    }

    @Override
    public ParameterizedClass<? extends Statement> getParameterizedClass() {
        List<ParameterizedClass<?>> list = new ArrayList<>();
        list.add(a.getParameterizedClass());
        list.add(b.getParameterizedClass());
        return new ParameterizedClass<>(Conjunction.class, list);
    }
}
