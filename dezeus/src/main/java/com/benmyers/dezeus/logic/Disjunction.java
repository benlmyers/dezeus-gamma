package com.benmyers.dezeus.logic;

import com.benmyers.dezeus.App;
import com.benmyers.dezeus.core.Operator;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.lang.Symbol;

public class Disjunction extends Operator {

    private Statement a, b;

    public Disjunction(Statement a, Statement b) {
        this.a = a;
        this.b = b;
    }

    public static int getOrder() {
        return 3;
    }

    public static Symbol getSymbol() {
        return Symbol.OR;
    }

    public Statement getLeft() {
        return a;
    }

    public Statement getRight() {
        return b;
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
