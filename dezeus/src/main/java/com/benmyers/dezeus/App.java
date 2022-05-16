package com.benmyers.dezeus;

import java.util.Scanner;

import com.benmyers.dezeus.core.Namespace;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.StatementBuilder;
import com.benmyers.dezeus.core.error.DezeusException;
import com.benmyers.dezeus.lang.DefaultSymbolSet;
import com.benmyers.dezeus.lang.SymbolSet;

public class App {

    public static final SymbolSet symbols = new DefaultSymbolSet();

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Dezeus!");
        System.out.println("------------------");
        System.out.println("Enter a statement below.");
        String input = scanner.nextLine();
        try {
            StatementBuilder builder = new StatementBuilder(input);
            Statement statement = builder.build();
            System.out.println("-");
            System.out.println(statement.toString());
            System.out.println(statement.toEnglish());
            System.out.println(statement.toLaTeX());
        } catch (DezeusException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }

    public static void resetSymbols() {
        Namespace.reset();
    }
}
