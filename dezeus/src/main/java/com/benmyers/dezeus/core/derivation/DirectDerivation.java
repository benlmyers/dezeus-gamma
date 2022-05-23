package com.benmyers.dezeus.core.derivation;

import com.benmyers.dezeus.core.Deduction;
import com.benmyers.dezeus.core.Statement;

public class DirectDerivation extends Derivation {

    private Deduction directDeduction;

    public DirectDerivation(Statement show, Deduction directDeduction) {
        super(show);
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
