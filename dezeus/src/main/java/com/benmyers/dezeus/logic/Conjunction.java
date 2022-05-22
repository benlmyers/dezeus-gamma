package com.benmyers.dezeus.logic;

import java.util.Set;

import com.benmyers.dezeus.App;
import com.benmyers.dezeus.core.Deduction;
import com.benmyers.dezeus.core.Operator;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.derivation.ConjunctionDerivation;
import com.benmyers.dezeus.core.derivation.Derivation;
import com.benmyers.dezeus.core.error.ProofNotFoundException;
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
    public Derivation show(Set<Deduction> knowns) throws ProofNotFoundException {
        Derivation leftDerivation = getLeft().show(knowns);
        Derivation rightDerivation = getRight().show(knowns);
        return new ConjunctionDerivation(this, leftDerivation, rightDerivation);
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
