package com.benmyers.dezeus.lang;

import java.util.EnumMap;
import java.util.Map;

public class DefaultSymbolSet implements SymbolSet {

    private Map<Symbol, String> map = new EnumMap<>(Symbol.class);

    public DefaultSymbolSet() {
        map.put(Symbol.AND, "^");
        map.put(Symbol.OR, "v");
        map.put(Symbol.NOT, "~");
        map.put(Symbol.IFF, "↔");
        map.put(Symbol.IMPLIES, "→");
    }

    @Override
    public String get(Symbol key) {
        return map.get(key);
    }
}
