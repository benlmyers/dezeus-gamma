package com.benmyers.dezeus.util;

import com.benmyers.dezeus.core.derivation.Derivation;

public class DerivationFormatter {

    private static int depth = 0;

    public static void print(Derivation derivation) {
        depth = 0;
        System.out.printf(pre() + "%-20s%s%n", derivation.getStatement().toString(), "[" + derivation.getAbbr() + "]");
    }

    private static String pre() {
        String _ = "";
        for (int i = 0; i < depth; i++) {
            _ += " ";
        }
        return _;
    }
}
