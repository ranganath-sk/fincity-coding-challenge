package com.example.fincity.coding.challenge.api.exceptions;

/**
 * Thrown when cars not found
 * 
 * @author Ranganatha S K
 *
 */
public class CarsNotFoundException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 6782916823537668120L;

  public CarsNotFoundException() {
    super("There is no cars exists!!");
  }
}
