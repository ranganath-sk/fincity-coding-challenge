package com.example.fincity.coding.challenge.alternateresponse;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.WebExchangeBindException;

/**
 * Service to build alternate responses
 * 
 * @author Ranganatha S K
 *
 */
@Service
public class AlternateResponseBuilderService {

  public AlternateResponse from(String developerMessage, Error error) {

    HashSet<Error> set = new HashSet<>();
    set.add(error);

    return new AlternateResponse(UUID.randomUUID().toString(), developerMessage, set);
  }

  public AlternateResponse from(WebExchangeBindException e) {

    return new AlternateResponse(UUID.randomUUID().toString(),
        "The request is not valid. Please correct it before re-submitting",
        mergeSets(mapAnyBindingResultFiledErrors(e.getBindingResult()),
            mapAnyBindingResultGlobalErrors(e.getBindingResult())));
  }

  @SuppressWarnings("null")
  private static Set<Error> mapAnyBindingResultGlobalErrors(BindingResult bindingResult) {
    if (bindingResult == null && !bindingResult.hasGlobalErrors()
        || bindingResult.getGlobalErrors().isEmpty()) {
      return new HashSet<Error>();
    } else {

      return bindingResult.getGlobalErrors().stream()
          .map(globalError -> new Error(globalError.getObjectName(), "entity", null,
              globalError.getDefaultMessage()))
          .collect(Collectors.toSet());
    }
  }

  @SuppressWarnings("null")
  private static Set<Error> mapAnyBindingResultFiledErrors(BindingResult bindingResult) {

    if (bindingResult == null && !bindingResult.hasFieldErrors()
        || bindingResult.getFieldErrors().isEmpty()) {
      return new HashSet<Error>();
    } else {

      return bindingResult.getFieldErrors().stream().map(fieldError -> new Error(
          fieldError.getObjectName(), fieldError.getField(),
          fieldError.getRejectedValue() != null ? fieldError.getRejectedValue().toString() : null,
          fieldError.getDefaultMessage() == null ? null
              : StringUtils.capitalize(fieldError.getDefaultMessage())))
          .collect(Collectors.toSet());
    }
  }

  @SuppressWarnings("serial")
  private static <T> Set<T> mergeSets(Set<T> set1, Set<T> set2) {
    return new HashSet<T>() {
      {
        addAll(set1);
        addAll(set2);
      }
    };
  }
}
