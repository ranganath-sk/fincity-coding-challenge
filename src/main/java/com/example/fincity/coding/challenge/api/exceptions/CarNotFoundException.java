package com.example.fincity.coding.challenge.api.exceptions;

import lombok.Getter;

/**
 * Thrown when car not found
 * 
 * @author Ranganatha S K
 *
 */
@Getter
public class CarNotFoundException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = -3406656184800900780L;
  private Long id;

  public CarNotFoundException(Long id) {
    super("Car details with id " + id + " not exits");
    this.id = id;
  }

}
