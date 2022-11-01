package com.benmyers.dezeus.core.rule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;

import com.benmyers.dezeus.core.Statement;

public abstract class Rule {

    String name = "Unnamed Rule";
    int id = 0;

    Set<Statement> input;
    Statement output;

    public final void writeToFile() throws IOException {
        File file = new File("rules/" + id + ".rule");
        file.createNewFile();
        // Write the id, name, input, and output to the file
        String value = id + "\n" + name + "\n" + input + "\n" + output;
        Files.write(file.toPath(), value.getBytes());
    }

    @Override
    public String toString() {
        // Get a string representing the rule's type
        String type = this.getClass().getSimpleName();
        return id + " [" + type + "]\n" + name + "\n" + input + " -> " + output;
    }
}