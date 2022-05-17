package com.benmyers.dezeus.core.justification;

public class PremiseJustification implements Justification {

    @Override
    public String getAbbr() {
        return "pr";
    }

    @Override
    public String getReason() {
        return "Premise provided";
    }
}
