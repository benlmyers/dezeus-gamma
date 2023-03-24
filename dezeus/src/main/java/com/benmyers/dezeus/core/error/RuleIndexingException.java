package com.benmyers.dezeus.core.error;

public class RuleIndexingException extends DezeusException {

    public RuleIndexingException() {
        super("The rules could not be indexed.");
    }
}
