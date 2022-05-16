package com.benmyers.dezeus.logic;

import com.benmyers.dezeus.App;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.lang.Symbol;

public class Disjunction extends Statement {

    public static final int ORDER = 1;

    private Statement a, b;

    public Disjunction(Statement a, Statement b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return wrapString(a) + App.symbols.get(Symbol.OR) + wrapString(b);
    }

    @Override
    public String toEnglish() {
        return wrapEngligh(a) + " or " + wrapEngligh(b);
    }

    @Override
    public String toLaTeX() {
        return wrapLaTeX(a) + " \\lor " + wrapLaTeX(b);
    }
}
