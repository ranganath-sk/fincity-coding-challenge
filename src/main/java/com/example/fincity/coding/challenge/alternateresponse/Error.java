package com.example.fincity.coding.challenge.alternateresponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Error DTO for alternate response
 * 
 * @author Ranganatha S K
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error {

  private String entity;
  private String propery;
  private String invalidValue;
  private String message;
}
