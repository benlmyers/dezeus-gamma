package com.benmyers.dezeus.core.justification;

public class ConditionalAssumptionJustification implements Justification {

    @Override
    public String getAbbr() {
        return "ass cd";
    }

    @Override
    public String getReason() {
        return "Assumption for conditional derivation";
    }
}
