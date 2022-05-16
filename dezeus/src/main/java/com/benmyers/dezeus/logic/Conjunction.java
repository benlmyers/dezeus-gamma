package com.benmyers.dezeus.logic;

import com.benmyers.dezeus.App;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.lang.Symbol;

public class Conjunction extends Statement {

    private Statement a, b;

    public Conjunction(Statement a, Statement b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return a + App.symbols.get(Symbol.AND) + b;
    }

    @Override
    public String toEnglish() {
        return a.toEnglish() + " and " + b.toEnglish();
    }

    @Override
    public String toLaTeX() {
        return a.toLaTeX() + "\\land" + b.toLaTeX();
    }
}
