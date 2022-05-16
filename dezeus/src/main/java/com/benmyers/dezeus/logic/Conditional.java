package com.benmyers.dezeus.logic;

import com.benmyers.dezeus.App;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.lang.Symbol;

public class Conditional extends Statement {

    private Statement ant, cons;

    public Conditional(Statement ant, Statement cons) {
        this.ant = ant;
        this.cons = cons;
    }

    @Override
    public String toString() {
        return formalizeString(ant) + App.symbols.get(Symbol.IMPLIES) + formalizeString(cons);
    }

    @Override
    public String toEnglish() {
        return formalizeEnglish(ant) + " implies " + formalizeEnglish(cons);
    }

    @Override
    public String toLaTeX() {
        return formalizeLaTeX(ant) + " \\Rightarrow " + formalizeLaTeX(cons);
    }
}
