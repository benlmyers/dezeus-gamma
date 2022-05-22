package com.benmyers.dezeus.core;

import java.util.HashSet;
import java.util.Set;

import com.benmyers.dezeus.core.derivation.Derivation;
import com.benmyers.dezeus.core.derivation.IndirectDerivation;
import com.benmyers.dezeus.core.error.ProofNotFoundException;
import com.benmyers.dezeus.core.justification.AssumptionIndirectJustification;
import com.benmyers.dezeus.logic.Biconditional;
import com.benmyers.dezeus.logic.Conditional;
import com.benmyers.dezeus.logic.Conjunction;
import com.benmyers.dezeus.logic.Disjunction;
import com.benmyers.dezeus.logic.Negation;

public abstract class Statement {

    public abstract String toString();

    public abstract String toEnglish();

    public abstract String toLaTeX();

    public Conjunction and(Statement b) {
        return new Conjunction(this, b);
    }

    public Disjunction or(Statement b) {
        return new Disjunction(this, b);
    }

    public Conditional implies(Statement cons) {
        return new Conditional(this, cons);
    }

    public Conditional when(Statement ant) {
        return new Conditional(ant, this);
    }

    public Biconditional iff(Statement b) {
        return new Biconditional(this, b);
    }

    public Negation not() {
        return new Negation(this);
    }

    public Derivation show(Set<Deduction> knowns) throws ProofNotFoundException {
        Deduction assumption = new Deduction(not(), new AssumptionIndirectJustification());
        Set<Deduction> newKnowns = new HashSet<>(knowns);
        newKnowns.add(assumption);
        // TODO: Main derivation logic.
        return new IndirectDerivation(this, null, null);
    }

    protected static String wrapString(Statement s) {
        if (s instanceof Atom) {
            return s.toString();
        } else {
            return "(" + s.toString() + ")";
        }
    }

    protected static String wrapEngligh(Statement s) {
        if (s instanceof Atom) {
            return s.toEnglish();
        } else {
            return "(" + s.toEnglish() + ")";
        }
    }

    protected static String wrapLaTeX(Statement s) {
        if (s instanceof Atom) {
            return s.toLaTeX();
        } else {
            return "(" + s.toLaTeX() + ")";
        }
    }
}
