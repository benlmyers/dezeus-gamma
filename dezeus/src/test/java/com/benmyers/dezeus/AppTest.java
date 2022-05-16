package com.benmyers.dezeus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.benmyers.dezeus.core.Atom;
import com.benmyers.dezeus.core.Statement;

import org.junit.Test;

public class AppTest {

    @Test
    public void checkNaming() {
        App.resetSymbols();
        Statement p;
        try {
            p = new Atom("P");
            assertEquals("P", p.toString());
            assertEquals("P", p.toEnglish());
            assertEquals("P", p.toLaTeX());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
