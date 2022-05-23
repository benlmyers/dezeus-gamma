package com.benmyers.dezeus;

import java.util.Scanner;

import com.benmyers.dezeus.core.Namespace;
import com.benmyers.dezeus.core.Proposition;
import com.benmyers.dezeus.core.PropositionBuilder;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.StatementBuilder;
import com.benmyers.dezeus.core.derivation.Derivation;
import com.benmyers.dezeus.core.error.DezeusException;
import com.benmyers.dezeus.core.error.ProofNotFoundException;
import com.benmyers.dezeus.lang.DefaultSymbolSet;
import com.benmyers.dezeus.lang.SymbolSet;

public class App {

    public static final SymbolSet symbols = new DefaultSymbolSet();

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Dezeus!");
        while (true) {
            System.out.println("------------------");
            System.out.println("What would you like to do?");
            System.out.println("[1] Symbolize");
            System.out.println("[2] Derive");
            System.out.println("[*] Exit");
            try {
                System.out.print(">> ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        symbolize();
                        break;
                    case 2:
                        derive();
                        break;
                    default:
                        return;
                }
            } catch (NumberFormatException e) {
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public static void resetSymbols() {
        Namespace.reset();
    }

    private static void symbolize() {
        System.out.println("Enter a statement below.");
        System.out.print(">> ");
        String input = scanner.nextLine();
        try {
            StatementBuilder builder = new StatementBuilder(input);
            Statement statement = builder.build();
            System.out.println("-");
            System.out.println("You entered: " + statement.toString());
            symbolizeMenu(statement);
        } catch (DezeusException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }

    private static void symbolizeMenu(Statement s) {
        System.out.println("-");
        System.out.println("[1] Convert to English");
        System.out.println("[2] Convert to LaTeX");
        System.out.println("[*] Symbolize again");
        System.out.println("[0] Menu");
        try {
            System.out.print(">> ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 0:
                    return;
                case 1:
                    System.out.println(s.toEnglish());
                    symbolizeMenu(s);
                    break;
                case 2:
                    System.out.println(s.toLaTeX());
                    symbolizeMenu(s);
                    break;
                default:
                    symbolize();
                    return;
            }
        } catch (NumberFormatException e) {
            symbolize();
        }
    }

    private static void derive() {
        System.out.println("Enter a proposition below.");
        System.out.print(">> ");
        String input = scanner.nextLine();
        try {
            PropositionBuilder builder = new PropositionBuilder(input);
            Proposition proposition = builder.build();
            System.out.println("-");
            System.out.println("You entered: " + proposition.toString());
            deriveMenu(proposition);
        } catch (DezeusException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }

    private static void deriveMenu(Proposition p) {
        System.out.println("-");
        System.out.println("[1] Prove the proposition");
        System.out.println("[*] Derive again");
        System.out.println("[0] Menu");
        try {
            System.out.print(">> ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 0:
                    return;
                case 1:
                    try {
                        Derivation derivation = p.prove();
                        System.out.println("Proved.");
                        derivation.print();
                    } catch (ProofNotFoundException i) {
                        System.out.println(i.getMessage());
                    }
                    deriveMenu(p);
                    break;
                default:
                    derive();
                    return;
            }
        } catch (NumberFormatException e) {
            symbolize();
        }
    }
}
