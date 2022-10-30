package com.benmyers.dezeus.util;

import com.benmyers.dezeus.core.Deduction;
import com.benmyers.dezeus.core.derivation.ConditionalDerivation;
import com.benmyers.dezeus.core.derivation.ConjunctionDerivation;
import com.benmyers.dezeus.core.derivation.Derivation;
import com.benmyers.dezeus.core.derivation.DirectDerivation;
import com.benmyers.dezeus.logic.Conjunction;

public class DerivationFormatter {

    private static int depth = 0;

    public static void print(Derivation derivation) {
        print(derivation, 0);
    }

    public static void print(Derivation derivation, int startingDepth) {
        depth = startingDepth;
        System.out.printf(pre() + "Show %-20s%s%n", derivation.getStatement().toString(),
                "[" + derivation.getAbbr() + "]");
        depth += 1;
        if (derivation instanceof ConditionalDerivation) {
            ConditionalDerivation conditionalDerivation = (ConditionalDerivation) derivation;
            System.out.println(conditionalDerivation.getAntDeduction().toString());
            Derivation consDerivation = conditionalDerivation.getConsDerivation();
            DerivationFormatter.print(consDerivation, depth);
        } else if (derivation instanceof DirectDerivation) {
            DirectDerivation directDerivation = (DirectDerivation) derivation;
            Deduction directDeduction = directDerivation.getDirectDeduction();
            System.out.println(directDeduction.toString());
        } else if (derivation instanceof ConjunctionDerivation) {
            ConjunctionDerivation conjunctionDerivation = (ConjunctionDerivation) derivation;
            Derivation leftDerivation = conjunctionDerivation.getLeftDerivation();
            Derivation rightDerivation = conjunctionDerivation.getRightDerivation();
            DerivationFormatter.print(leftDerivation, depth);
            DerivationFormatter.print(rightDerivation, depth);
        } else {
            System.out.println("ID");
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
