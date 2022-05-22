package com.benmyers.dezeus.logic;

import java.util.Set;

import com.benmyers.dezeus.App;
import com.benmyers.dezeus.core.Deduction;
import com.benmyers.dezeus.core.Operator;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.derivation.ConditionalDerivation;
import com.benmyers.dezeus.core.derivation.ConjunctionDerivation;
import com.benmyers.dezeus.core.derivation.Derivation;
import com.benmyers.dezeus.core.error.ProofNotFoundException;
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

    @Override
    public Derivation show(Set<Deduction> knowns) throws ProofNotFoundException {
        Conditional leftConditional = new Conditional(getLeft(), getRight());
        Conditional rightConditional = new Conditional(getRight(), getLeft());
        Derivation leftDerivation = leftConditional.show(knowns);
        Derivation rightDerivation = rightConditional.show(knowns);
        return new ConjunctionDerivation(getLeft().and(getRight()), leftDerivation, rightDerivation);
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
}
