package com.benmyers.dezeus.core.derivation;

import java.util.HashSet;

import com.benmyers.dezeus.core.Deduction;

public class Deducer {

    HashSet<Deduction> knowns;

    public Deducer(HashSet<Deduction> knowns) {
        this.knowns = knowns;
    }
}
