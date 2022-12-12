package com.benmyers.dezeus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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

    @Test
    public void checkStatementFitting() {
        App.resetSymbols();
        try {
            Statement a = new Atom("A");
            Statement b = new Atom("B");
            Statement c = new Atom("C");
            Statement p = new Atom("P");
            Statement q = new Atom("Q");
            Statement r = new Atom("R");
            Statement _1 = a.and(b);
            Statement _2 = a.implies(b).implies(c);
            Statement _3 = a.implies(b.implies(c));
            Statement _4 = a.and(b).iff(c);
            Statement _5 = a.not().not();
            Statement __1 = p.and(q);
            Statement __2 = p.implies(q);
            Statement __3 = q.not();
            Statement __4 = p.implies(p);
            Statement __5 = p.implies(q.implies(r));
            assertTrue(a.fits(p));
            assertTrue(_1.fits(p));
            assertTrue(_2.fits(p));
            assertTrue(_1.fits(__1));
            assertTrue(_2.fits(__2));
            assertFalse(_3.fits(__3));
            assertFalse(_1.fits(__3));
            assertFalse(_2.fits(__4));
            assertFalse(_3.fits(__3));
            assertTrue(_3.fits(__5));
            assertFalse(_2.fits(__3));
            assertFalse(_4.fits(__2));
            assertTrue(_5.fits(__3));
        } catch (DezeusException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void checkComplexity() {
        App.resetSymbols();
        try {
            Statement a = new Atom("A");
            Statement b = new Atom("B");
            Statement c = new Atom("C");
            Statement d = new Atom("D");
            assertEquals(1, a.complexity());
            assertEquals(2, a.and(b).complexity());
            assertEquals(2, a.iff(b).complexity());
            assertEquals(1, a.not().complexity());
            assertEquals(2, a.and(a).complexity());
            assertEquals(3, a.iff(b).iff(c).complexity());
            assertEquals(4, a.and(b).implies(c.implies(d)).complexity());
            assertEquals(5, a.and(b).and(c).and(d).or(a).complexity());
        } catch (DezeusException e) {
            fail(e.getMessage());
        }
    }
}
