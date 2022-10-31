package com.benmyers.dezeus.core.error;

public class RuleNotFoundException extends DezeusException {

    public RuleNotFoundException() {
        super("No suitable rules could be deducted for use in derivation.");
    }
}