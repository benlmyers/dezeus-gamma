package com.benmyers.dezeus.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.benmyers.dezeus.App;
import com.benmyers.dezeus.core.Atom;
import com.benmyers.dezeus.core.Operator;
import com.benmyers.dezeus.core.Statement;
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

    @Override
    public List<Atom> getAtoms() {
        List<Atom> atoms = new ArrayList<>();
        atoms.addAll(a.getAtoms());
        atoms.addAll(b.getAtoms());
        return atoms;
    }

    @Override
    public void setAtoms(Map<Atom, Statement> map) {
        a = map.get(a);
        b = map.get(b);
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
}
