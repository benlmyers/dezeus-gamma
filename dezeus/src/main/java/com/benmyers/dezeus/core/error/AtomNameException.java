package com.benmyers.dezeus.core.error;

public class AtomNameException extends DezeusException {

    public AtomNameException() {
        super("All new variables must use unique names.");
    }
}
