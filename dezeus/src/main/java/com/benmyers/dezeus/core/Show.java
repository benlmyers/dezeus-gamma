package com.benmyers.dezeus.core;

import com.benmyers.dezeus.core.derivation.Deducer;
import com.benmyers.dezeus.core.error.ShowFailedException;
import com.benmyers.dezeus.core.error.ShowFailedException.Reason;
import com.benmyers.dezeus.core.justification.AssumeJustification;
import com.benmyers.dezeus.core.justification.AssumeJustification.Type;
import com.benmyers.dezeus.logic.Conditional;
import com.benmyers.dezeus.logic.Negation;

public class Show {

    private DeductionGroup deductions;
    private StatementGroup showItems;

    private Show(DeductionGroup deductions, Statement show) {
        this.deductions = deductions;
        if (show instanceof StatementGroup) {
            this.showItems = (StatementGroup) show;
        } else {
            this.showItems = new StatementGroup(show);
        }
    }

    public static Show indirect(DeductionGroup deductions, Statement conclusion) {
        DeductionGroup _deductions = deductions;
        Negation negation = new Negation(conclusion);
        Deduction deduction = new Deduction(negation, new AssumeJustification(AssumeJustification.Type.INDIRECT));
        _deductions.add(deduction);
        return new Show(_deductions, conclusion);
    }

    public static Show direct(DeductionGroup deductions, Statement conclusion) {
        return new Show(deductions, conclusion);
    }

    public static Show direct(DeductionGroup deductions, StatementGroup conclusions) {
        return new Show(deductions, conclusions);
    }

    public static Show conditional(DeductionGroup deductions, Conditional conclusion) {
        DeductionGroup _deductions = deductions;
        Statement assumption = conclusion.getAnt();
        Deduction deduction = new Deduction(assumption, new AssumeJustification(Type.CONDITIONAL));
        _deductions.add(deduction);
        return new Show(_deductions, conclusion);
    }

    public DeductionGroup attempt() throws ShowFailedException {
        DeductionGroup result = new DeductionGroup();
        for (Statement conclusion : showItems) {
            Deduction deduction = attempt(conclusion);
            result.add(deduction);
        }
        return result;
    }

    private Deduction attempt(Statement conclusion) throws ShowFailedException {
        Deducer deducer = new Deducer(deductions);
        DeductionGroup _deductions = deducer.getDeductions();
        for (Deduction d : _deductions) {
            if (d.getStatement().equals(conclusion)) {
                return d;
            }
        }
        throw new ShowFailedException(Reason.NO_CONCLUSION_FOUND);
    }

    @Override
    public String toString() {
        String str = "";
        for (Statement show : this.showItems) {
            str += "Show " + show + ":\n";
        }
        return str;
    }
}
