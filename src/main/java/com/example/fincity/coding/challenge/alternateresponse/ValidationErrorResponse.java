package com.example.fincity.coding.challenge.alternateresponse;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Validation response DTO
 * 
 * @author Ranganahta S K
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorResponse {

  private String responseId;
  private String developerMessage;
  private List<String> message;
}
