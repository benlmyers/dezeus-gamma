package com.benmyers.dezeus.core;

import java.util.HashSet;
import java.util.Set;

import com.benmyers.dezeus.core.invalidity.Invalidity;
import com.benmyers.dezeus.core.justification.AssumptionConditionalJustification;
import com.benmyers.dezeus.core.justification.AssumptionIndirectJustification;
import com.benmyers.dezeus.logic.Conditional;
import com.benmyers.dezeus.logic.Negation;

public class Show {

    private Set<? extends Deduction> deductions;
    private Statement statement;

    public Show(Statement statement, Set<? extends Deduction> deductions) {
        this.statement = statement;
        this.deductions = deductions;
    }

    public Derivation show() throws Invalidity {
        if (statement instanceof Conditional) {
            Conditional conditional = (Conditional) statement;
            Deduction assumption = new Deduction(conditional.getAnt(), new AssumptionConditionalJustification());
            Set<Deduction> newDeductions = new HashSet<>(deductions);
            newDeductions.add(assumption);
            Show showCons = new Show(conditional.getCons(), newDeductions);
            Derivation consDerivation = showCons.show();
            if (consDerivation != null)
                return Derivation.CONDITIONAL;
        } else {
            Negation negation = statement.not();
            Deduction assumption = new Deduction(negation, new AssumptionIndirectJustification());
            Set<Deduction> newDeductions = new HashSet<>(deductions);
            newDeductions.add(assumption);
            // TODO: Find conflicts
        }
        return null;
    }
}
