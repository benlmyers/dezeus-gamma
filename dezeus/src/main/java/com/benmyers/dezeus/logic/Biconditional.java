package com.benmyers.dezeus.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.benmyers.dezeus.App;
import com.benmyers.dezeus.core.Atom;
import com.benmyers.dezeus.core.DeductionGroup;
import com.benmyers.dezeus.core.Operator;
import com.benmyers.dezeus.core.ParameterizedClass;
import com.benmyers.dezeus.core.Show;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.StatementGroup;
import com.benmyers.dezeus.lang.Symbol;

public class Biconditional extends Operator {

    private Statement a;
    private Statement b;

    public Biconditional(Statement a, Statement b) {
        this.a = a;
        this.b = b;
    }

    public static int getOrder() {
        return 5;
    }

    public static Symbol getSymbol() {
        return Symbol.IFF;
    }

    public Statement getLeft() {
        return a;
    }

    public Statement getRight() {
        return b;
    }

    public Biconditional copy() {
        return new Biconditional(a.copy(), b.copy());
    }

    @Override
    public Show show(DeductionGroup deductions) {
        Conditional bcl = new Conditional(a, b);
        Conditional bcr = new Conditional(b, a);
        return Show.direct(deductions, new StatementGroup(bcl, bcr));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Biconditional) {
            Biconditional statement = (Biconditional) obj;
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
        return wrapString(a) + App.symbols.get(Symbol.IFF) + wrapString(b);
    }

    @Override
    public String toEnglish() {
        return wrapEngligh(a) + " if and only if " + wrapEngligh(b);
    }

    @Override
    public String toLaTeX() {
        return wrapLaTeX(a) + " \\Leftrightarrow " + wrapLaTeX(b);
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
        return new ParameterizedClass<>(Biconditional.class, list);
    }
}
