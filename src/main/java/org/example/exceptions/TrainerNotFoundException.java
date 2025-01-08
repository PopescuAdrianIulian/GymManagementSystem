package org.example.exceptions;

public class TrainerNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Trainer not found!";

    public TrainerNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
