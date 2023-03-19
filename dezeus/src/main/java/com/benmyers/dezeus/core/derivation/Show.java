package com.benmyers.dezeus.core.derivation;

import java.util.HashSet;
import java.util.Set;

import com.benmyers.dezeus.core.Deduction;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.error.ShowFailedException;
import com.benmyers.dezeus.core.justification.AssumeJustification;
import com.benmyers.dezeus.logic.Negation;

public class Show {

    private Set<Deduction> deductions;
    private Set<Statement> show;

    private Show(Set<Deduction> deductions, Statement show) {
        this.deductions = deductions;
        this.show = new HashSet<>();
        this.show.add(show);
    }

    private Show(Set<Deduction> deductions, Set<Statement> show) {
        this.deductions = deductions;
        this.show = show;
    }

    public static Show indirect(Set<Deduction> deductions, Statement conclusion) {
        Set<Deduction> _deductions = new HashSet<>(deductions);
        Negation negation = new Negation(conclusion);
        Deduction deduction = new Deduction(negation, new AssumeJustification(AssumeJustification.Type.INDIRECT));
        _deductions.add(deduction);
        return new Show(_deductions, conclusion);
    }

    public static Show direct(Set<Deduction> deductions, Statement conclusion) {
        return new Show(deductions, conclusion);
    }

    public Deduction attempt() throws ShowFailedException {
        return null;
    }
}
