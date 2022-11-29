package com.benmyers.dezeus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.benmyers.dezeus.core.Atom;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.StatementBuilder;
import com.benmyers.dezeus.core.error.DezeusException;
import com.benmyers.dezeus.lang.Symbol;

import org.junit.Test;

public class SymbolizationTest {

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

    @Test
    public void checkInformalSymbolization() {
        App.resetSymbols();
        try {
            Statement p = new Atom("P");
            Statement q = new Atom("Q");
            Statement r = new Atom("R");
            Statement a = p.and(q).and(r);
            Statement b = p.or(q.and(r));
            Statement c = p.implies(q).iff(r);
            Statement d = p.not().or(q);
            StatementBuilder aBuild = new StatementBuilder("P ^ Q ^ R");
            StatementBuilder bBuild = new StatementBuilder("P v Q ^ R");
            StatementBuilder cBuild = new StatementBuilder("P -> Q <-> R");
            StatementBuilder dBuild = new StatementBuilder("~P v Q");
            assertEquals(a.toString(), aBuild.build().toString());
            assertEquals(b.toString(), bBuild.build().toString());
            assertEquals(c.toString(), cBuild.build().toString());
            assertEquals(d.toString(), dBuild.build().toString());
        } catch (DezeusException e) {
            fail(e.getMessage());
        }
    }
}
