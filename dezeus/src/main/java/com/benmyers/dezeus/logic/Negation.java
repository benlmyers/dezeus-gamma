package com.benmyers.dezeus.logic;

import com.benmyers.dezeus.App;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.lang.Symbol;

public class Negation extends Statement {

    public static final int ORDER = 2;

    private Statement s;

    public Negation(Statement s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return App.symbols.get(Symbol.NOT) + wrapString(s);
    }

    @Override
    public String toEnglish() {
        return "not" + wrapEngligh(s);
    }

    @Override
    public String toLaTeX() {
        return "\\neg" + wrapLaTeX(s);
    }
}
