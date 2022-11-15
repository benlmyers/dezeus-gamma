package com.benmyers.dezeus.core.error;

public class ApplyMismatchException extends DezeusException {

    public ApplyMismatchException() {
        super("You tried to apply a rule, but your arguments did not match the rule's input.");
    }
}
