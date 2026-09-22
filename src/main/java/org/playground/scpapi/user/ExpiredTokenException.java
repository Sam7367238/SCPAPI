package org.playground.scpapi.user;

public class ExpiredTokenException extends RuntimeException {
    public ExpiredTokenException() {
        super("The token has already expired");
    }
}
