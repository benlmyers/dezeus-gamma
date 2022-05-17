package com.benmyers.dezeus.lang;

import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.logic.Biconditional;
import com.benmyers.dezeus.logic.Conditional;
import com.benmyers.dezeus.logic.Conjunction;
import com.benmyers.dezeus.logic.Disjunction;
import com.benmyers.dezeus.logic.Negation;

public enum Symbol {
    MISSING, AND, OR, IMPLIES, IFF, NOT;

    public Class<? extends Statement> getStatementType() {
        switch (this) {
            case MISSING:
                return null;
            case AND:
                return Conjunction.class;
            case OR:
                return Disjunction.class;
            case NOT:
                return Negation.class;
            case IMPLIES:
                return Conditional.class;
            case IFF:
                return Biconditional.class;
        }
        return null;
    }
}
