package org.playground.scpapi.media;

public class InvalidMimeTypeException extends RuntimeException {
    public InvalidMimeTypeException() {
        super("This type of file is invalid");
    }
}
