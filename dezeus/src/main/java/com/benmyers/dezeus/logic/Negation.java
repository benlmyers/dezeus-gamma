package com.benmyers.dezeus.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.benmyers.dezeus.App;
import com.benmyers.dezeus.core.Atom;
import com.benmyers.dezeus.core.Operator;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.lang.Symbol;

public class Negation extends Operator {

    private Statement s;

    public Negation(Statement s) {
        this.s = s;
    }

    public static int getOrder() {
        return 1;
    }

    public static Symbol getSymbol() {
        return Symbol.NOT;
    }

    @Override
    public List<Atom> getAtoms() {
        List<Atom> atoms = new ArrayList<>();
        atoms.addAll(s.getAtoms());
        return atoms;
    }

    @Override
    public void setAtoms(Map<Atom, Statement> map) {
        s = map.get(s);
    }

    @Override
    public String toString() {
        return App.symbols.get(Symbol.NOT) + wrapString(s);
    }

    @Override
    public String toEnglish() {
        return "not" + wrapEngligh(s);
    }

    @Override
    public String toLaTeX() {
        return "\\neg" + wrapLaTeX(s);
    }
}
