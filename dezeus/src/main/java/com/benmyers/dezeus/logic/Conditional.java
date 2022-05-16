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
