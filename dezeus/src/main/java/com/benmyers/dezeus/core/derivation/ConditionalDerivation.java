package com.benmyers.dezeus.core.derivation;

import com.benmyers.dezeus.core.Deduction;
import com.benmyers.dezeus.core.Statement;

public class ConditionalDerivation extends Derivation {

    private Deduction antDeduction;
    private Derivation consDerivation;

    public ConditionalDerivation(Statement show, Deduction antDeduction, Derivation consDerivation) {
        super(show);
        this.antDeduction = antDeduction;
        this.consDerivation = consDerivation;
    }

    public Deduction getAntDeduction() {
        return antDeduction;
    }

    public Derivation getConsDerivation() {
        return consDerivation;
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
