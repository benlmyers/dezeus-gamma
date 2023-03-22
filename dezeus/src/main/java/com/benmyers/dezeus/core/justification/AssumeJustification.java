package com.benmyers.dezeus.core.justification;

public class AssumeJustification implements Justification {

    public enum Type {
        INDIRECT,
        CONDITIONAL
    }

    private Type type;

    public AssumeJustification(Type type) {
        this.type = type;
    }

    @Override
    public String getAbbr() {
        switch (type) {
            case INDIRECT:
                return "ass id";
            case CONDITIONAL:
                return "ass cd";
            default:
                return "show";
        }
    }

    @Override
    public String getReason() {
        switch (type) {
            case INDIRECT:
                return "Assume Indirect";
            case CONDITIONAL:
                return "Assume Conditional";
            default:
                return "Show";
        }
    }
}