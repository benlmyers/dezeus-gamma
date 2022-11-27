package com.benmyers.dezeus.core.error;

public class InstatiateMismatchException extends DezeusException {

    public InstatiateMismatchException() {
        super("You tried to instaniate a statement using an incorrect number of arguments.");
    }
}
