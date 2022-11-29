package com.benmyers.dezeus.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.benmyers.dezeus.App;
import com.benmyers.dezeus.core.Atom;
import com.benmyers.dezeus.core.Operator;
import com.benmyers.dezeus.core.ParameterizedClass;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.StatementGroup;
import com.benmyers.dezeus.lang.Symbol;

public class Conditional extends Operator {

    private Statement ant, cons;

    public Conditional(Statement ant, Statement cons) {
        this.ant = ant;
        this.cons = cons;
    }

    public static int getOrder() {
        return 4;
    }

    public static Symbol getSymbol() {
        return Symbol.IMPLIES;
    }

    public Statement getAnt() {
        return ant;
    }

    public Statement getCons() {
        return cons;
    }

    public Conditional copy() {
        return new Conditional(ant.copy(), cons.copy());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Conditional) {
            Conditional statement = (Conditional) obj;
            return statement.getAnt().equals(ant) && statement.getCons().equals(cons);
        }
        return false;
    }

    @Override
    public List<Atom> getAtoms() {
        List<Atom> atoms = new ArrayList<>();
        atoms.addAll(ant.getAtoms());
        atoms.addAll(cons.getAtoms());
        return atoms;
    }

    @Override
    public String toString() {
        return wrapString(ant) + App.symbols.get(Symbol.IMPLIES) + wrapString(cons);
    }

    @Override
    public String toEnglish() {
        return wrapEngligh(ant) + " implies " + wrapEngligh(cons);
    }

    @Override
    public String toLaTeX() {
        return wrapLaTeX(ant) + " \\Rightarrow " + wrapLaTeX(cons);
    }

    @Override
    public void setAtoms(Map<Atom, Statement> map) {
        if (ant instanceof Atom) {
            ant = map.get(ant);
        } else {
            ant.setAtoms(map);
        }
        if (cons instanceof Atom) {
            cons = map.get(cons);
        } else {
            cons.setAtoms(map);
        }
    }

    @Override
    public List<Statement> getParameters() {
        List<Statement> list = new ArrayList<>();
        list.add(ant);
        list.add(cons);
        return list;
    }

    @Override
    public ParameterizedClass<? extends Statement> getParameterizedClass() {
        List<ParameterizedClass<? extends Statement>> list = new ArrayList<>();
        list.add(ant.getParameterizedClass());
        list.add(cons.getParameterizedClass());
        return new ParameterizedClass<>(Conditional.class, list);
    }
}
