package org.playground.scpapi.media;

public class EmptyFileException extends RuntimeException {
    public EmptyFileException() {
        super("This file is empty");
    }
}
