package com.benmyers.dezeus.core.derivation;

import com.benmyers.dezeus.core.Deduction;

public class ConditionalDerivation extends Derivation {

    private Deduction antDeduction;
    private Derivation consDerivation;

    public ConditionalDerivation(Deduction antDeduction, Derivation consDerivation) {
        this.antDeduction = antDeduction;
        this.consDerivation = consDerivation;
    }

    @Override
    public String getAbbr() {
        return "cd";
    }

    @Override
    public String getReason() {
        return "Conditional derivation";
    }
}
