package com.benmyers.dezeus.logic;

import java.util.HashSet;
import java.util.Set;

import com.benmyers.dezeus.App;
import com.benmyers.dezeus.core.Deduction;
import com.benmyers.dezeus.core.Operator;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.derivation.ConditionalDerivation;
import com.benmyers.dezeus.core.derivation.Derivation;
import com.benmyers.dezeus.core.error.ProofNotFoundException;
import com.benmyers.dezeus.core.justification.ConditionalAssumptionJustification;
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

    @Override
    public Derivation show(Set<Deduction> knowns) throws ProofNotFoundException {
        Deduction assumption = new Deduction(getAnt(), new ConditionalAssumptionJustification());
        Set<Deduction> newKnowns = new HashSet<>(knowns);
        newKnowns.add(assumption);
        Derivation consDerivation = getCons().show(newKnowns);
        return new ConditionalDerivation(this, assumption, consDerivation);
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
}
