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
        return formalizeString(a) + App.symbols.get(Symbol.IFF) + formalizeString(b);
    }

    @Override
    public String toEnglish() {
        return formalizeEnglish(a) + " if and only if " + formalizeEnglish(b);
    }

    @Override
    public String toLaTeX() {
        return formalizeLaTeX(a) + " \\Leftrightarrow " + formalizeLaTeX(b);
    }
}
