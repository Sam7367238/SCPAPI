package org.playground.scpapi.user;

public class NonMatchingPasswordsException extends RuntimeException {
    public NonMatchingPasswordsException() {
        super("The passwords do not match");
    }
}
