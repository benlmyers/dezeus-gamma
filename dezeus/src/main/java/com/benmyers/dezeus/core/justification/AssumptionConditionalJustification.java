package com.benmyers.dezeus.core.justification;

public class AssumptionConditionalJustification implements Justification {

    @Override
    public String getAbbr() {
        return "ass cd";
    }

    @Override
    public String getReason() {
        return "Assumption for conditional derivation";
    }
}
