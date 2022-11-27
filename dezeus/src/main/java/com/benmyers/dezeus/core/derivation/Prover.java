package com.benmyers.dezeus.core.derivation;

import java.util.Set;

import com.benmyers.dezeus.core.Deduction;
import com.benmyers.dezeus.core.Namespace;
import com.benmyers.dezeus.core.Proof;
import com.benmyers.dezeus.core.Proposition;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.error.ProofNotFoundException;

public class Prover {

    Set<Deduction> knowns;
    Statement conclusion;

    Namespace namespace;

    public Prover(Set<Deduction> knowns, Statement conclusion) {
        this.knowns = knowns;
        this.conclusion = conclusion;
    }

    public Prover(Proposition proposition) {
        this.knowns = proposition.getPremisesAsDeductions();
        this.conclusion = proposition.getConclusion();
    }

    public Proof prove() throws ProofNotFoundException {
        return null;
    }
}
