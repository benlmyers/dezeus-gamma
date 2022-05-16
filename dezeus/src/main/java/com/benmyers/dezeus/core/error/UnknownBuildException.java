package com.benmyers.dezeus.core.error;

public class UnknownBuildException extends StatementBuilderException {

    public UnknownBuildException(String issue) {
        super("Dezeus is not able to symbolize " + issue);
    }
}
