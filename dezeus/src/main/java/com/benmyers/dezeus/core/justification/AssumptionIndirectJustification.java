package com.benmyers.dezeus.core.justification;

public class AssumptionIndirectJustification implements Justification {

    @Override
    public String getAbbr() {
        return "ass id";
    }

    @Override
    public String getReason() {
        return "Assumption for indirect derivation";
    }

}
