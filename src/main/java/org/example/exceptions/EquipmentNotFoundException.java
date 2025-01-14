package org.example.exceptions;

public class EquipmentNotFoundException extends RuntimeException {
  private static final String DEFAULT_MESSAGE = "Equipment not found!";
  public EquipmentNotFoundException() {
    super(DEFAULT_MESSAGE);
  }
}
