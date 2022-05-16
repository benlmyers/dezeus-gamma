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
        return ant + App.symbols.get(Symbol.IMPLIES) + cons;
    }

    @Override
    public String toEnglish() {
        return ant.toEnglish() + " implies " + cons.toEnglish();
    }

    @Override
    public String toLaTeX() {
        return ant.toLaTeX() + " \\Rightarrow " + cons.toLaTeX();
    }
}
