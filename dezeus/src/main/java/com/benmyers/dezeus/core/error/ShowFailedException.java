package com.benmyers.dezeus.core.error;

public class ShowFailedException extends DezeusException {

    public ShowFailedException() {
        super("This statement could not be shown.");
    }
}
