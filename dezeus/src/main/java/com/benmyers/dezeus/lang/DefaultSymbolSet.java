package com.benmyers.dezeus.lang;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

import com.benmyers.dezeus.App;

public class DefaultSymbolSet extends SymbolSet {

    private Map<Symbol, String> map = new EnumMap<>(Symbol.class);

    public DefaultSymbolSet() {
        map.put(Symbol.MISSING, "?");
        map.put(Symbol.AND, "^");
        map.put(Symbol.OR, "v");
        map.put(Symbol.NOT, "~");
        map.put(Symbol.IFF, "<->");
        map.put(Symbol.IMPLIES, "->");
    }

    @Override
    public String get(Symbol key) {
        return map.get(key);
    }

    @Override
    public ArrayList<String> getAll() {
        Symbol[] keys = Symbol.class.getEnumConstants();
        ArrayList<String> val = new ArrayList<>();
        for (Symbol key : keys)
            val.add(App.symbols.get(key));
        return val;
    }
}
