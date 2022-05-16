package com.benmyers.dezeus.lang.error;

import com.benmyers.dezeus.core.error.StatementBuilderException;

public class SymbolNotFoundException extends StatementBuilderException {

    public SymbolNotFoundException(String unknownSymbol) {
        super("No symbol \"" + unknownSymbol + "\" found.");
    }
}
