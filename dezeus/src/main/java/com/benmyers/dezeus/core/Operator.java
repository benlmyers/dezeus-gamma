package com.benmyers.dezeus.core;

import com.benmyers.dezeus.lang.Symbol;

public abstract class Operator extends Statement {

    public static Symbol getSymbol() {
        return Symbol.MISSING;
    }

    public static int getOrder() {
        return -1;
    }
}
