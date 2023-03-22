package com.benmyers.dezeus.core.error;

public class ShowFailedException extends DezeusException {

    private Reason reason;

    public enum Reason {
        UNKNOWN,
        NO_CONCLUSION_FOUND
    }

    public ShowFailedException(Reason reason) {
        super("This statement could not be shown.");
        this.reason = reason;
    }

    public ShowFailedException() {
        this(Reason.UNKNOWN);
    }

    public Reason getReason() {
        return reason;
    }
}
