package com.benmyers.dezeus.core.derivation;

import com.benmyers.dezeus.core.Statement;

public class ConjunctionDerivation extends Derivation {

    private Derivation leftDerivation;
    private Derivation rightDerivation;

    public ConjunctionDerivation(Statement show, Derivation leftDerivation, Derivation rightDerivation) {
        super(show);
        this.leftDerivation = leftDerivation;
        this.rightDerivation = rightDerivation;
    }

    public Derivation getLeftDerivation() {
        return leftDerivation;
    }

    public Derivation getRightDerivation() {
        return rightDerivation;
    }

    @Override
    public String getAbbr() {
        return "cjd";
    }

    @Override
    public String getReason() {
        return "Conjunction derivation";
    }
}
