package com.benmyers.dezeus.logic;

import com.benmyers.dezeus.App;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.lang.Symbol;

public class Biconditional extends Statement {

    private Statement a;
    private Statement b;

    public Biconditional(Statement a, Statement b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return a + App.symbols.get(Symbol.IFF) + b;
    }

    @Override
    public String toEnglish() {
        return a.toEnglish() + " if and only if " + b.toEnglish();
    }

    @Override
    public String toLaTeX() {
        return a.toEnglish() + " \\Leftrightarrow " + b.toLaTeX();
    }
}
