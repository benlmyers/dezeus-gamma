package com.benmyers.dezeus.core.error;

public class AtomNameException extends Exception {

    public AtomNameException() {
        super("All new variables must use unique names.", null, true, false);
    }
}
