package com.benmyers.dezeus.core.error;

public class UnknownBuildException extends StatementBuilderException {

    public UnknownBuildException() {
        super("A statement could not be found for the given String.");
    }
}
