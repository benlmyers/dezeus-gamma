package com.benmyers.dezeus.core;

public class Proof {

    Proposition proposition;

    Statement assumption;
    Deduction conclusion;

    public Proof(Proposition proposition, Statement assumption, Deduction conclusion) {
        this.proposition = proposition;
        this.assumption = assumption;
        this.conclusion = conclusion;
    }
}
