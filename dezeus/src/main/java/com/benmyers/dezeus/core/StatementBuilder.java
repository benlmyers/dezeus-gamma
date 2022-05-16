package com.benmyers.dezeus.core;

import com.benmyers.dezeus.App;
import com.benmyers.dezeus.core.error.DezeusException;
import com.benmyers.dezeus.core.error.InvalidGroupingException;
import com.benmyers.dezeus.core.error.UnknownBuildException;
import com.benmyers.dezeus.lang.Symbol;
import com.benmyers.dezeus.logic.Biconditional;
import com.benmyers.dezeus.logic.Conditional;
import com.benmyers.dezeus.logic.Conjunction;
import com.benmyers.dezeus.logic.Disjunction;
import com.benmyers.dezeus.logic.Negation;

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
        int bestDepth = Integer.MAX_VALUE;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == ' ')
                continue;
            if (c == '(')
                depth += 1;
            else if (c == ')')
                depth -= 1;
            Symbol operator = App.symbols.getFrom(String.valueOf(c));
            if (operator != null && depth < bestDepth) {
                bestDepth = depth;
                best = operator;
                symbolIndex = i;
            }
        }
        if (depth != 0)
            throw new InvalidGroupingException(depth);
        if (symbolIndex < 0 || best == null)
            return new Atom(input);
        String left = input.substring(0, symbolIndex - 1);
        String right = input.substring(symbolIndex + 1, input.length() - 1);
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
                throw new UnknownBuildException();
        }
    }

    private String removeSpaces(String input) {
        return input.replaceAll("\\s", "");
    }

    private String removeOutsideBraces(String input) {
        if (input.charAt(0) == '(' && input.charAt(input.length() - 1) == ')') {
            return input.substring(1, input.length() - 1);
        }
        return input;
    }
}
