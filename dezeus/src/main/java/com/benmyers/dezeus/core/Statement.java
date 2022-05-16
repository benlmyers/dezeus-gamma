package com.benmyers.dezeus.core;

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

    public Negation not(Statement s) {
        return new Negation(s);
    }
}
