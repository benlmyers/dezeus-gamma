package com.benmyers.dezeus.core.error;

import com.benmyers.dezeus.App;
import com.benmyers.dezeus.lang.Symbol;

public class ImproperPropositionException extends DezeusException {

    public ImproperPropositionException() {
        super("Your proposition was built incorrectly. Ensure you use \"" + App.symbols.get(Symbol.PREMISE_DELIMITER)
                + "\" to seperate premises, and \""
                + App.symbols.get(Symbol.THEREFORE) + "\" to seperate the premises and conclusion.");
    }
}
