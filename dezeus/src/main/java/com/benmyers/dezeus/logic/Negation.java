package com.benmyers.dezeus.logic;

import com.benmyers.dezeus.App;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.lang.Symbol;

public class Negation extends Statement {

    private Statement s;

    public Negation(Statement s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return App.symbols.get(Symbol.NOT) + s;
    }

    @Override
    public String toEnglish() {
        return "not " + s.toEnglish();
    }

    @Override
    public String toLaTeX() {
        return "\\neg " + s.toLaTeX();
    }
}
