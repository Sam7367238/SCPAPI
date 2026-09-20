package org.playground.scpapi.user;

public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException() {
        super("A user is already registered");
    }
}
