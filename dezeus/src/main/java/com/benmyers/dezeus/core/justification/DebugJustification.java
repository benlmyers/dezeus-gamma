package com.benmyers.dezeus.core.justification;

public class DebugJustification implements Justification {

    @Override
    public String getAbbr() {
        throw new UnsupportedOperationException("DEBUG");
    }

    @Override
    public String getReason() {
        throw new UnsupportedOperationException("DEBUG");
    }

}
