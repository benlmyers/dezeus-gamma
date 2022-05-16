package com.benmyers.dezeus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.benmyers.dezeus.core.Atom;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.StatementBuilder;
import com.benmyers.dezeus.core.error.DezeusException;
import com.benmyers.dezeus.lang.Symbol;

import org.junit.Test;

public class SymbolTest {

    @Test
    public void checkLogicOperators() {
        App.resetSymbols();
        try {
            Statement p = new Atom("P");
            Statement q = new Atom("Q");
            Statement r = p.and(q);
            Statement s = p.or(q);
            Statement t = p.implies(q);
            assertEquals(r.toString(), p + App.symbols.get(Symbol.AND) + q);
            assertEquals(s.toString(), p + App.symbols.get(Symbol.OR) + q);
            assertEquals(t.toString(), p + App.symbols.get(Symbol.IMPLIES) + q);
        } catch (DezeusException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void checkSymbolization() {
        App.resetSymbols();
        try {
            Statement p = new Atom("P");
            Statement q = new Atom("Q");
            Statement a = p.and(q);
            Statement b = p.or(q);
            Statement c = p.iff(q);
            Statement d = a.implies(b);
            StatementBuilder aBuild = new StatementBuilder("P^Q");
            StatementBuilder bBuild = new StatementBuilder("P v Q");
            StatementBuilder cBuild = new StatementBuilder("P  <->  Q");
            StatementBuilder dBuild = new StatementBuilder("(P^Q) -> (PvQ)");
            assertEquals(a.toString(), aBuild.build().toString());
            assertEquals(b.toString(), bBuild.build().toString());
            assertEquals(c.toString(), cBuild.build().toString());
            assertEquals(d.toString(), dBuild.build().toString());
        } catch (DezeusException e) {
            fail(e.getMessage());
        }
    }
}
