package com.benmyers.dezeus.core.error;

public class InstantiateMismatchException extends DezeusException {

    public InstantiateMismatchException() {
        super("You tried to instaniate a statement using an incorrect number of arguments for atoms.");
    }
}
