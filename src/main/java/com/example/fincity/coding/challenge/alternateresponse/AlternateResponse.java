package com.example.fincity.coding.challenge.alternateresponse;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Alternate response DTO
 * 
 * @author Ranganahta S K
 *
 */

@Data
@NoArgsConstructor
public class AlternateResponse {

  private String responseId;
  private String developerMessage;
  private Set<Error> errors;

  AlternateResponse(String responseId, String developerMessage, Collection<Error> errors) {

    this.responseId = responseId;
    this.developerMessage = developerMessage;

    if (errors == null || errors.isEmpty()) {
      this.errors = null;
    } else {
      this.errors = errors.stream().collect(Collectors.toSet());
    }
  }
}
