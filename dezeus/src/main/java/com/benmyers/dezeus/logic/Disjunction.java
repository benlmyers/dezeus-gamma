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
        return a + App.symbols.getSymbol(Symbol.OR) + b;
    }

    @Override
    public String toEnglish() {
        return a.toEnglish() + " or " + b.toEnglish();
    }

    @Override
    public String toLaTeX() {
        return a.toLaTeX() + "\\lor" + b.toLaTeX();
    }
}
