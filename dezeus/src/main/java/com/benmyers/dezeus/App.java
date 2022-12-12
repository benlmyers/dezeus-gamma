package com.benmyers.dezeus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.benmyers.dezeus.core.Atom;
import com.benmyers.dezeus.core.Deduction;
import com.benmyers.dezeus.core.Namespace;
import com.benmyers.dezeus.core.Proposition;
import com.benmyers.dezeus.core.PropositionBuilder;
import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.StatementBuilder;
import com.benmyers.dezeus.core.StatementGroup;
import com.benmyers.dezeus.core.derivation.Arranger;
import com.benmyers.dezeus.core.derivation.Deriver;
import com.benmyers.dezeus.core.derivation.Prover;
import com.benmyers.dezeus.core.error.DezeusException;
import com.benmyers.dezeus.core.error.ProofNotFoundException;
import com.benmyers.dezeus.core.rule.Law;
import com.benmyers.dezeus.core.rule.Rule;
import com.benmyers.dezeus.core.rule.RulesManager;
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
            System.out.println("[3] Rules");
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
                    case 3:
                        rulesMenu();
                        break;
                    default:
                        return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public static void resetSymbols() {
        Namespace.reset();
    }

    private static void rulesMenu() {
        System.out.println("-");
        System.out.println("[1] List All Rules");
        System.out.println("[2] Define Law");
        System.out.println("[3] Apply a Rule");
        System.out.println("[4] Instantiate a Rule");
        System.out.println("[5] Arrange Relevant for Rule Use");
        System.out.println("[6] Arrange Any for Rule Use");
        System.out.println("[7] Get All Derivations from Rule");
        System.out.println("[*] Menu");
        try {
            System.out.print(">> ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    RulesManager manager = new RulesManager();
                    manager.listAll();
                    break;
                case 2:
                    defineRule();
                    break;
                case 3:
                    applyRule();
                    break;
                case 4:
                    try {
                        System.out.println("Enter the ID of the rule you wish to instantiate:");
                        System.out.print(">> ");
                        int id = Integer.parseInt(scanner.nextLine());
                        Rule rule = new RulesManager().get(id);
                        System.out.println("You chose: " + rule);
                        List<Atom> atoms = rule.getAtoms();
                        List<Statement> instantiation = new ArrayList<>();
                        System.out.println("This rule has " + atoms.size() + " parameters: " + atoms);
                        System.out.println("Enter the values for each parameter:");
                        for (int i = 0; i < atoms.size(); i++) {
                            System.out.print(">> " + atoms.get(i) + " := ");
                            String value = scanner.nextLine();
                            instantiation.add(new StatementBuilder(value).build());
                        }
                        Rule newRule = rule.instantiate(instantiation);
                        System.out.println("Instantiated rule: " + newRule);
                    } catch (DezeusException e) {
                        System.out.println("Invalid ID");
                    }
                    break;
                case 5:
                    try {
                        System.out.println("Enter the ID of the rule you wish to use:");
                        System.out.print(">> ");
                        int id = Integer.parseInt(scanner.nextLine());
                        Rule rule = new RulesManager().get(id);
                        System.out.println("You chose: " + rule);
                        StatementGroup input = rule.getInput();
                        System.out.println("This rule has " + input.size() + " inputs: " + input);
                        System.out.println("Enter your relevant statements for use.");
                        System.out.println("You may enter your statements in any order.");
                        StatementGroup list = new StatementGroup();
                        for (int i = 0; i < input.size(); i++) {
                            System.out.print(">> (" + (i + 1) + "): ");
                            String value = scanner.nextLine();
                            list.add(new StatementBuilder(value).build());
                        }
                        Arranger arranger = new Arranger(list, rule);
                        List<Statement> result = arranger.arrangeRelevant();
                        System.out.println("Result: " + result);
                    } catch (DezeusException e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    try {
                        System.out.println("Enter the ID of the rule you wish to use:");
                        System.out.print(">> ");
                        int id = Integer.parseInt(scanner.nextLine());
                        Rule rule = new RulesManager().get(id);
                        System.out.println("You chose: " + rule);
                        StatementGroup input = rule.getInput();
                        System.out.println("This rule has " + input.size() + " inputs: " + input);
                        System.out.println("Enter any amount of statements for use.");
                        System.out.println("Enter \"0\" when you're done.");
                        StatementGroup list = new StatementGroup();
                        while (true) {
                            System.out.print(">> ");
                            String in = scanner.nextLine();
                            if (in.equals("0"))
                                break;
                            list.add(new StatementBuilder(in).build());
                        }
                        Arranger arranger = new Arranger(list, rule);
                        List<StatementGroup> result = arranger.arrangeAny();
                        List<Statement> ruleInputList = new ArrayList<>(rule.getInput());
                        System.out.println("Result: ");
                        for (int i = 0; i < rule.getInput().size(); i++) {
                            System.out.println(ruleInputList.get(i) + ": " + result.get(i));
                        }
                    } catch (DezeusException e) {
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    try {
                        System.out.println("Enter the ID of the rule you wish to use:");
                        System.out.print(">> ");
                        int id = Integer.parseInt(scanner.nextLine());
                        Rule rule = new RulesManager().get(id);
                        System.out.println("You chose: " + rule);
                        StatementGroup input = rule.getInput();
                        System.out.println("This rule has " + input.size() + " inputs: " + input);
                        System.out.println("Enter any amount of statements for use.");
                        System.out.println("Enter \"0\" when you're done.");
                        StatementGroup list = new StatementGroup();
                        while (true) {
                            System.out.print(">> ");
                            String in = scanner.nextLine();
                            if (in.equals("0"))
                                break;
                            list.add(new StatementBuilder(in).build());
                        }
                        Deriver deriver = new Deriver(list, rule);
                        Set<Deduction> deductions = deriver.deriveAny();
                        System.out.println("Result: ");
                        for (Deduction deduction : deductions) {
                            System.out.println(deduction);
                        }
                    } catch (DezeusException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    return;
            }
        } catch (NumberFormatException e) {
            symbolize();
        }
    }

    private static void applyRuleMenu(StatementGroup statements) {
        System.out.println("-");
        System.out.println("[1] Apply Specific Rule");
        System.out.println("[*] Menu");
        int id;
        Rule rule;
        try {
            System.out.print(">> ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Enter the ID of the rule you wish to apply:");
                    System.out.print(">> ");
                    id = Integer.parseInt(scanner.nextLine());
                    rule = new RulesManager().get(id);
                    System.out.println("You chose: " + rule);
                    try {
                        Deriver deriver = new Deriver(statements, rule);
                        Set<Deduction> result = deriver.deriveRelevant();
                        System.out.println("Result: " + result);
                    } catch (DezeusException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    return;
            }
        } catch (NumberFormatException e) {
            symbolize();
        }
    }

    private static void applyRule() {
        System.out.println("Enter a series of statements:");
        System.out.print(">> ");
        String input = scanner.nextLine();
        // Get set of inputs period-seperated
        String[] inputs = input.split("\\.");
        // For each input, create a statement using StatementBuilder
        StatementGroup statements = new StatementGroup();
        try {
            for (String statement : inputs) {
                statements.add(new StatementBuilder(statement).build());
            }
            System.out.println("-");
            System.out.println("You entered: " + statements);
            applyRuleMenu(statements);
        } catch (DezeusException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }

    private static void defineRule() {
        System.out.println("Enter a proposition:");
        System.out.print(">> ");
        String input = scanner.nextLine();
        System.out.println("Enter an ID value:");
        System.out.print(">> ");
        int id = Integer.parseInt(scanner.nextLine());
        try {
            PropositionBuilder builder = new PropositionBuilder(input);
            Proposition proposition = builder.build();
            System.out.println("-");
            System.out.println("You entered: " + proposition.toString());
            System.out.println("Saving...");
            Law law = new Law(id, proposition);
            law.writeToFile();
        } catch (Exception e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
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
        System.out.println("[3] Show Parameterized Class");
        System.out.println("[4] Fit Against Rule Template");
        System.out.println("[5] Complexity");
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
                case 3:
                    System.out.println(s.getParameterizedClass());
                    break;
                case 4:
                    System.out.println("You entered: " + s);
                    System.out.println("Enter a template statement:");
                    System.out.print(">> ");
                    String input = scanner.nextLine();
                    try {
                        StatementBuilder builder = new StatementBuilder(input);
                        Statement statement = builder.build();
                        System.out.println("-");
                        System.out.println("You entered: " + statement.toString());
                        System.out.println("Fits: " + s.fits(statement));
                    } catch (DezeusException e) {
                        System.out.println("An error occured.");
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    System.out.println("Complexity: " + s.complexity());
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
                        Prover prover = new Prover(p);
                        prover.prove();
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
