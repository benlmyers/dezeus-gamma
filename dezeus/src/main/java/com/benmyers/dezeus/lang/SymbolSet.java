package com.benmyers.dezeus.lang;

import java.util.ArrayList;

import com.benmyers.dezeus.lang.error.SymbolNotFoundException;

public abstract class SymbolSet {

    public abstract String get(Symbol key);

    public abstract ArrayList<String> getAll();

    public Symbol getFrom(String value) throws SymbolNotFoundException {
        Symbol[] symbols = Symbol.class.getEnumConstants();
        for (Symbol symbol : symbols) {
            if (get(symbol).equals(value))
                return symbol;
        }
        throw new SymbolNotFoundException(value);
    }
}