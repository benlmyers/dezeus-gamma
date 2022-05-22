package com.benmyers.dezeus.core.error;

public class ProofNotFoundException extends DezeusException {

    public ProofNotFoundException() {
        super("No proof could be found for the derivation.");
    }
}
