package com.benmyers.dezeus.core.derivation;

import java.util.Set;

import com.benmyers.dezeus.core.Namespace;
import com.benmyers.dezeus.core.Proof;
import com.benmyers.dezeus.core.Proposition;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.error.ProofNotFoundException;

public class Prover {

    Set<Statement> knowns;
    Statement conclusion;

    Namespace namespace;

    public Prover(Set<Statement> knowns, Statement conclusion) {

    }

    public Prover(Proposition proposition) {

    }

    public Proof prove() throws ProofNotFoundException {
        return null;
    }
}
