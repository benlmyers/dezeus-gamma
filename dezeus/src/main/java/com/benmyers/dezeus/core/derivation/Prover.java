package com.benmyers.dezeus.core.derivation;

import java.util.Set;

import com.benmyers.dezeus.core.Deduction;
import com.benmyers.dezeus.core.Namespace;
import com.benmyers.dezeus.core.Proof;
import com.benmyers.dezeus.core.Proposition;
import com.benmyers.dezeus.core.StatementGroup;
import com.benmyers.dezeus.core.error.ProofNotFoundException;
import com.benmyers.dezeus.core.error.ShowFailedException;

public class Prover {

    Proposition proposition;

    Namespace namespace;

    public Prover(StatementGroup knowns, StatementGroup conclusion) {
        this.proposition = new Proposition(knowns, conclusion);
    }

    public Prover(Proposition proposition) {
        this.proposition = proposition;
    }

    public Proof prove() throws ProofNotFoundException {
        Set<Deduction> deductions = proposition.getPremisesAsDeductions();
        StatementGroup conclusions = proposition.getConclusions();
        Show show = conclusions.show(deductions);
        try {
            show.attempt();
        } catch (ShowFailedException e) {
            e.printStackTrace();
            throw new ProofNotFoundException();
        }
        return null;
    }
}
