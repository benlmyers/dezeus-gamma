package com.benmyers.dezeus.core.error;

public class InvalidGroupingException extends StatementBuilderException {

    public InvalidGroupingException(int finalDepth) {
        super("Your expression is not properly grouped. Extra opening braces: " + finalDepth);
    }
}
