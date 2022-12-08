package com.benmyers.dezeus.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.benmyers.dezeus.App;
import com.benmyers.dezeus.core.error.DezeusException;
import com.benmyers.dezeus.core.error.InvalidGroupingException;
import com.benmyers.dezeus.core.error.UnknownBuildException;
import com.benmyers.dezeus.lang.Symbol;
import com.benmyers.dezeus.lang.error.SymbolNotFoundException;
import com.benmyers.dezeus.logic.Biconditional;
import com.benmyers.dezeus.logic.Conditional;
import com.benmyers.dezeus.logic.Conjunction;
import com.benmyers.dezeus.logic.Disjunction;
import com.benmyers.dezeus.logic.Negation;
import com.benmyers.dezeus.util.Debug;

public class StatementBuilder {

    private String input;

    public StatementBuilder(String input) {
        this.input = input;
    }

    public StatementGroup buildGroup() throws DezeusException {
        if (!input.contains(App.symbols.get(Symbol.PREMISE_DELIMITER))) {
            return new StatementGroup(build());
        }
        StatementGroup group = new StatementGroup();
        String[] split = input.split("\\" + App.symbols.get(Symbol.PREMISE_DELIMITER));
        for (String string : split) {
            StatementBuilder builder = new StatementBuilder(string);
            group.add(builder.build());
        }
        return group;
    }

    @SuppressWarnings("unchecked")
    public Statement build() throws DezeusException {
        input = removeSpaces(input);
        int depth = 0;
        Symbol best = null;
        int symbolIndex = -1;
        int symbolLength = -1;
        int bestOrder = Integer.MIN_VALUE;
        int bestDepth = Integer.MAX_VALUE;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == ' ')
                continue;
            if (c == '(')
                depth += 1;
            else if (c == ')')
                depth -= 1;
            int maxSymoblLength = App.symbols.maxSymoblLength();
            for (int j = 1; j <= maxSymoblLength; j++) {
                if (i + j >= input.length())
                    break;
                String s = input.substring(i, i + j);
                try {
                    Symbol symbol = App.symbols.getFrom(String.valueOf(s));
                    Class<? extends Statement> symbolType = symbol.getStatementType();
                    if (depth <= bestDepth && Operator.class.isAssignableFrom(symbolType)) {
                        Class<? extends Operator> operatorClass = (Class<? extends Operator>) symbolType;
                        Method orderMethod = operatorClass.getMethod("getOrder");
                        int order = (int) orderMethod.invoke(null);
                        if (depth == bestDepth && order < bestOrder)
                            break;
                        bestDepth = depth;
                        bestOrder = order;
                        best = symbol;
                        symbolIndex = i;
                        symbolLength = j;
                        break;
                    }
                } catch (SymbolNotFoundException | InvocationTargetException | IllegalAccessException
                        | NoSuchMethodException e) {
                }
            }
        }
        if (depth != 0)
            throw new InvalidGroupingException(depth);
        Debug.log("INPUT: " + input + " (" + App.symbols.get(best) + ") [" + bestDepth + "]");
        if (symbolIndex < 0 || best == null)
            return new Atom(input, true);
        String left = input.substring(0, symbolIndex);
        String right = input.substring(symbolIndex + symbolLength);
        Debug.log("L: " + left);
        Debug.log("R: " + right);
        left = removeOutsideBraces(left);
        right = removeOutsideBraces(right);
        StatementBuilder leftBuilder = new StatementBuilder(left);
        StatementBuilder rightBuilder = new StatementBuilder(right);
        switch (best) {
            case NOT:
                return new Negation(rightBuilder.build());
            case AND:
                return new Conjunction(leftBuilder.build(), rightBuilder.build());
            case OR:
                return new Disjunction(leftBuilder.build(), rightBuilder.build());
            case IMPLIES:
                return new Conditional(leftBuilder.build(), rightBuilder.build());
            case IFF:
                return new Biconditional(leftBuilder.build(), rightBuilder.build());
            default:
                throw new UnknownBuildException(input);
        }
    }

    private String removeSpaces(String input) {
        return input.replaceAll("\\s", "");
    }

    private String removeOutsideBraces(String input) {
        if (input.length() == 0)
            return input;
        if (input.charAt(0) == '(' && input.charAt(input.length() - 1) == ')') {
            return input.substring(1, input.length() - 1);
        }
        return input;
    }
}
