package com.benmyers.dezeus.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.benmyers.dezeus.core.derivation.Show;
import com.benmyers.dezeus.core.error.InstantiateMismatchException;
import com.benmyers.dezeus.core.error.ShowFailedException;
import com.benmyers.dezeus.core.util.Copyable;
import com.benmyers.dezeus.logic.Biconditional;
import com.benmyers.dezeus.logic.Conditional;
import com.benmyers.dezeus.logic.Conjunction;
import com.benmyers.dezeus.logic.Disjunction;
import com.benmyers.dezeus.logic.Negation;

public abstract class Statement implements Copyable<Statement> {

    public abstract String toString();

    public abstract String toEnglish();

    public abstract String toLaTeX();

    public abstract List<Atom> getAtoms();

    public abstract void setAtoms(Map<Atom, Statement> map);

    public abstract List<Statement> getParameters();

    @SuppressWarnings("java:S1452")
    public abstract ParameterizedClass<? extends Statement> getParameterizedClass();

    @Override
    public abstract boolean equals(Object obj);

    public int complexity() {
        int total = 0;
        for (Statement parameter : getParameters()) {
            total += parameter.complexity();
        }
        return total;
    }

    public Statement instantiate(List<Statement> arguments) throws InstantiateMismatchException {
        List<Atom> atoms = getAtoms();
        if (atoms.size() != arguments.size()) {
            throw new InstantiateMismatchException();
        }
        Map<Atom, Statement> map = new HashMap<>();
        for (int i = 0; i < atoms.size(); i++) {
            map.put(atoms.get(i), arguments.get(i));
        }
        Statement copy = this.copy();
        copy.setAtoms(map);
        return copy;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

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

    public Show show(Set<Deduction> deductions) {
        return Show.indirect(deductions, this);
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

    public boolean fits(Statement template) {
        Map<Atom, Statement> map = new HashMap<>();
        return fits(template, map);
    }

    public boolean fits(Statement template, Map<Atom, Statement> map) {
        if (template instanceof Atom) {
            Atom atom = (Atom) template;
            if (map.get(atom) != null && map.get(atom) != this) {
                return false;
            }
            map.put(atom, this);
            return true;
        } else if (template.getClass() != getClass()) {
            return false;
        } else {
            List<Statement> parameters = getParameters();
            List<Statement> templateParameters = template.getParameters();
            if (parameters.size() != templateParameters.size()) {
                return false;
            }
            for (int i = 0; i < parameters.size(); i++) {
                Statement parameter = parameters.get(i);
                Statement templateParameter = templateParameters.get(i);
                if (!parameter.fits(templateParameter, map)) {
                    return false;
                }
            }
        }
        return true;
    }
}
