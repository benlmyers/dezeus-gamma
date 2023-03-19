package com.benmyers.dezeus.core.justification;

public class ShowJustification implements Justification {

    public enum Type {
        CONJUNCTION,
        INDIRECT
    }

    public Type type;

    @Override
    public String getAbbr() {
        switch (type) {
            case CONJUNCTION:
                return "conj";
            case INDIRECT:
                return "id";
            default:
                return "show";
        }
    }

    @Override
    public String getReason() {
        switch (type) {
            case CONJUNCTION:
                return "Show Conjugation";
            case INDIRECT:
                return "Indirect Contradiction";
            default:
                return "Show";
        }
    }
}