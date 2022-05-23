package com.benmyers.dezeus.util;

import com.benmyers.dezeus.core.derivation.ConditionalDerivation;
import com.benmyers.dezeus.core.derivation.Derivation;

public class DerivationFormatter {

    private static int depth = 0;

    public static void print(Derivation derivation, int startingDepth) {
        depth = startingDepth;
        System.out.printf(pre() + "%-20s%s%n", derivation.getStatement().toString(), "[" + derivation.getAbbr() + "]");
        if (derivation instanceof ConditionalDerivation) {
            // Print the assumption for conditional derivation
            depth += 1;
            ConditionalDerivation conditionalDerivation = (ConditionalDerivation) derivation;
            System.out.println(conditionalDerivation.getAntDeduction().toString());
            Derivation consDerivation = conditionalDerivation.getConsDerivation();
            DerivationFormatter.print(consDerivation, depth);
            // Perform the show operation upon the consequent
        }
    }

    private static String pre() {
        String _ = "";
        for (int i = 0; i < depth; i++) {
            _ += " ";
        }
        return _;
    }
}
