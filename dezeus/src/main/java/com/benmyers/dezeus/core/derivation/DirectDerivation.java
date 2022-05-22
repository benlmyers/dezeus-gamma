package com.benmyers.dezeus.core.derivation;

import com.benmyers.dezeus.core.Deduction;

public class DirectDerivation extends Derivation {

    private Deduction directDeduction;

    public DirectDerivation(Deduction directDeduction) {
        this.directDeduction = directDeduction;
    }

    @Override
    public String getAbbr() {
        return "dd";
    }

    @Override
    public String getReason() {
        return "Direct derivation";
    }
}
