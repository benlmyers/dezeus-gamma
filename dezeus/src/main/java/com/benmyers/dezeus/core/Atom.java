package com.benmyers.dezeus.core;

public class Atom extends Statement {

    private String name;
    private String latex;

    public Atom(String name) throws Exception {
        VariableNamespace.registerName(name);
        this.name = name;
        this.latex = " " + name + " ";
    }

    public Atom(String name, String latex) throws Exception {
        this(name);
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