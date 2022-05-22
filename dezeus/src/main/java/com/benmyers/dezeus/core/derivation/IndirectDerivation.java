package com.benmyers.dezeus.core.derivation;

import com.benmyers.dezeus.core.Deduction;

public class IndirectDerivation extends Derivation {

    private Deduction deductionA;
    private Deduction deductionB;

    public IndirectDerivation(Deduction deductionA, Deduction deductionB) {
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