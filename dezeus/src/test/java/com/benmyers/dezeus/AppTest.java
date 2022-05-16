package com.benmyers.dezeus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.benmyers.dezeus.core.Atom;
import com.benmyers.dezeus.core.Statement;

import org.junit.Test;

public class AppTest {

    @Test
    public void checkNaming() {
        Statement p;
        try {
            p = new Atom("p");
            assertEquals("p", p.toString());
            assertEquals("p", p.toEnglish());
            assertEquals(" p ", p.toLaTeX());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
