package com.example.fincity.coding.challenge.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.example.fincity.coding.challenge.alternateresponse.AlternateResponse;
import com.example.fincity.coding.challenge.alternateresponse.AlternateResponseBuilderService;
import com.example.fincity.coding.challenge.alternateresponse.Error;
import com.example.fincity.coding.challenge.alternateresponse.ValidationErrorResponse;
import com.example.fincity.coding.challenge.api.exceptions.CarNotFoundException;
import com.example.fincity.coding.challenge.api.exceptions.CarsNotFoundException;
import lombok.RequiredArgsConstructor;

/**
 * ControllerAdvice for CarController
 * 
 * @author Ranganatha S K
 *
 */

@RestControllerAdvice
@RequiredArgsConstructor
public class CarControllerAdvice extends ResponseEntityExceptionHandler {

  private final AlternateResponseBuilderService alternateResponseBuilderService;

  @ExceptionHandler(CarNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<AlternateResponse> handleCarNotFoundException(CarNotFoundException ex) {

    return buildAlternateResponseEntity(HttpStatus.NOT_FOUND,
        alternateResponseBuilderService.from(
            "The request is not valid. Please correct it before re-submitting",
            new Error("car", "id", ex.getId().toString(), ex.getLocalizedMessage())));
  }

  @ExceptionHandler(CarsNotFoundException.class)
  @ResponseStatus()
  public ResponseEntity<AlternateResponse> handleCarsNotFoundException(CarsNotFoundException ex) {

    return buildAlternateResponseEntity(HttpStatus.BAD_REQUEST, alternateResponseBuilderService
        .from(ex.getLocalizedMessage(), new Error("cars", null, null, "none found")));
  }

  @ExceptionHandler(AuthenticationException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ResponseEntity<AlternateResponse> handleUnauthorizedException(AuthenticationException ex) {

    return buildAlternateResponseEntity(HttpStatus.UNAUTHORIZED,
        alternateResponseBuilderService.from(ex.getMessage(), new Error(null, null, null,
            "You're uauthorized to make this call. Try agains with valid credentials")));
  }

  @ExceptionHandler(Forbidden.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ResponseEntity<AlternateResponse> handleForbiddenException(Forbidden ex) {

    return buildAlternateResponseEntity(HttpStatus.UNAUTHORIZED,
        alternateResponseBuilderService.from(ex.getMessage(), new Error(null, null, null,
            "You're uauthorized to make this call. Try agains with valid credentials")));
  }

  @ExceptionHandler(WebExchangeBindException.class)
  public ResponseEntity<AlternateResponse> handleWebExchageBindingException(
      WebExchangeBindException ex) {

    return buildAlternateResponseEntity(HttpStatus.BAD_REQUEST,
        alternateResponseBuilderService.from(ex));
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    List<String> details = new ArrayList<>();
    for (ObjectError error : ex.getBindingResult().getAllErrors()) {
      details.add(error.getDefaultMessage());
    }

    ValidationErrorResponse errorResponse =
        new ValidationErrorResponse(UUID.randomUUID().toString(),
            "The request is not valid, please correct it before re-submitting", details);

    return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
  }

  private ResponseEntity<AlternateResponse> buildAlternateResponseEntity(HttpStatus responseStatus,
      AlternateResponse alternateResponse) {

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);

    return new ResponseEntity<AlternateResponse>(alternateResponse, httpHeaders, responseStatus);
  }
}
