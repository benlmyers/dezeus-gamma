package com.benmyers.dezeus.core.rule;

import com.benmyers.dezeus.core.Proof;
import com.benmyers.dezeus.core.Proposition;

public class Theorem {

    private Proposition proposition;
    private Proof proof;

    public Theorem(Proposition proposition, Proof proof) {
        this.proposition = proposition;
        this.proof = proof;
    }

    public Proposition getProposition() {
        return proposition;
    }

    public Proof getProof() {
        return proof;
    }
}
