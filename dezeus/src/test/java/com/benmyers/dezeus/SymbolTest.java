package com.benmyers.dezeus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.benmyers.dezeus.core.Atom;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.lang.Symbol;

import org.junit.Test;

public class SymbolTest {

    @Test
    public void checkLogicOperators() {
        App.resetSymbols();
        try {
            Statement p = new Atom("p");
            Statement q = new Atom("q");
            Statement r = p.and(q);
            Statement s = p.or(q);
            Statement t = p.implies(q);
            assertEquals(r.toString(), p + App.symbols.get(Symbol.AND) + q);
            assertEquals(s.toString(), p + App.symbols.get(Symbol.OR) + q);
            assertEquals(t.toString(), p + App.symbols.get(Symbol.IMPLIES) + q);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
