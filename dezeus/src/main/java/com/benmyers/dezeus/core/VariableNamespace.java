package com.benmyers.dezeus.core;

import java.util.ArrayList;
import java.util.List;

import com.benmyers.dezeus.core.error.AtomNameException;

public class VariableNamespace {

    private static final List<String> usedNames = new ArrayList<>();

    public static void registerName(String name) throws Exception {
        if (usedNames.contains(name)) {
            throw new AtomNameException();
        }
        usedNames.add(name);
    }
}
