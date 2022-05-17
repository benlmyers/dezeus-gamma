package com.benmyers.dezeus.logic;

import com.benmyers.dezeus.App;
import com.benmyers.dezeus.core.Operator;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.lang.Symbol;

public class Biconditional extends Operator {

    private Statement a;
    private Statement b;

    public Biconditional(Statement a, Statement b) {
        this.a = a;
        this.b = b;
    }

    public static int getOrder() {
        return 5;
    }

    public static Symbol getSymbol() {
        return Symbol.IFF;
    }

    @Override
    public String toString() {
        return wrapString(a) + App.symbols.get(Symbol.IFF) + wrapString(b);
    }

    @Override
    public String toEnglish() {
        return wrapEngligh(a) + " if and only if " + wrapEngligh(b);
    }

    @Override
    public String toLaTeX() {
        return wrapLaTeX(a) + " \\Leftrightarrow " + wrapLaTeX(b);
    }
}
