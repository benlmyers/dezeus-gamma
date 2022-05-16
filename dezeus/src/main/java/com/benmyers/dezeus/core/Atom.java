package com.benmyers.dezeus.core;

import com.benmyers.dezeus.core.error.DezeusException;

public class Atom extends Statement {

    private String name;
    private String latex;

    public Atom(String name) throws DezeusException {
        this(name, name, false);
    }

    public Atom(String name, String latex) throws DezeusException {
        this(name, latex, false);
    }

    Atom(String name, Boolean bypass) throws DezeusException {
        this(name, name, bypass);
    }

    Atom(String name, String latex, Boolean bypass) throws DezeusException {
        if (!bypass)
            Namespace.registerName(name);
        this.name = name;
        this.latex = latex;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String toEnglish() {
        return name;
    }

    @Override
    public String toLaTeX() {
        return latex;
    }
}