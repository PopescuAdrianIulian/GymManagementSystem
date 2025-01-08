package org.example.exceptions;

public class MemberNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "member not found!";

    public MemberNotFoundException() {
        super(DEFAULT_MESSAGE);

    }
}
