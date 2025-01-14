package org.example.exceptions;

public class ReservationNotFoundException extends RuntimeException {
  private static final String DEFAULT_MESSAGE = "Reservation not found!";
    public ReservationNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
