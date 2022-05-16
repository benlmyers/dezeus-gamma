package com.benmyers.dezeus.lang;

import java.util.EnumMap;
import java.util.Map;

public class DefaultSymbolSet implements SymbolSet {

    private Map<Symbol, String> symbol = new EnumMap<>(Symbol.class);

    @Override
    public String getSymbol(Symbol key) {
        return symbol.get(key);
    }
}
