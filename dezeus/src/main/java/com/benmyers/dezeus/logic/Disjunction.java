package com.benmyers.dezeus.logic;

import com.benmyers.dezeus.App;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.lang.Symbol;

public class Disjunction extends Statement {

    private Statement a, b;

    public Disjunction(Statement a, Statement b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return formalizeString(a) + App.symbols.get(Symbol.OR) + formalizeString(b);
    }

    @Override
    public String toEnglish() {
        return formalizeEnglish(a) + " or " + formalizeEnglish(b);
    }

    @Override
    public String toLaTeX() {
        return formalizeLaTeX(a) + " \\lor " + formalizeLaTeX(b);
    }
}
