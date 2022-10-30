package com.benmyers.dezeus.core;

public class Theorem {

    private Proposition proposition;

    Theorem(Proposition proposition) {
        this.proposition = proposition;
    }

    public Proposition getProposition() {
        return proposition;
    }
}
