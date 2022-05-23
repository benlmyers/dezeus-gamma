package com.benmyers.dezeus.core.derivation;

import com.benmyers.dezeus.core.Deduction;
import com.benmyers.dezeus.core.Statement;

public class IndirectDerivation extends Derivation {

    private Deduction deductionA;
    private Deduction deductionB;

    public IndirectDerivation(Statement show, Deduction deductionA, Deduction deductionB) {
        super(show);
        this.deductionA = deductionA;
        this.deductionB = deductionB;
    }

    @Override
    public String getAbbr() {
        return "id";
    }

    @Override
    public String getReason() {
        return "Indirect derivation";
    }
}