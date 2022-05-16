package com.benmyers.dezeus.core;

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

    public Statement build() throws DezeusException {
        input = removeSpaces(input);
        int depth = 0;
        Symbol best = null;
        int symbolIndex = -1;
        int symbolLength = -1;
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
                    Symbol operator = App.symbols.getFrom(String.valueOf(s));
                    if (operator != null && depth < bestDepth) {
                        bestDepth = depth;
                        best = operator;
                        symbolIndex = i;
                        symbolLength = j;
                        break;
                    }
                } catch (SymbolNotFoundException e) {
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
