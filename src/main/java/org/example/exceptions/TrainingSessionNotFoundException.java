package org.example.exceptions;

public class TrainingSessionNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Training Session not found!";

    public TrainingSessionNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
