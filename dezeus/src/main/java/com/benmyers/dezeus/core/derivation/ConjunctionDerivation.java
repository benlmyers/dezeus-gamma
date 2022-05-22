package com.benmyers.dezeus.core.derivation;

public class ConjunctionDerivation extends Derivation {

    private Derivation leftDerivation;
    private Derivation rightDerivation;

    public ConjunctionDerivation(Derivation leftDerivation, Derivation rightDerivation) {
        this.leftDerivation = leftDerivation;
        this.rightDerivation = rightDerivation;
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
