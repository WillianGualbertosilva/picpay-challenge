package br.com.picpay.api.exceptionhandler;

import br.com.picpay.api.dtos.ExceptionDTO;
import br.com.picpay.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class APIExceptionHandler {

  private ObjectError objectError;

  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<ExceptionDTO> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
    ExceptionDTO exceptionDTO = new ExceptionDTO(
      HttpStatus.BAD_REQUEST.value(),
      HttpStatus.BAD_REQUEST.name(),
      exception.getMessage()
    );
    return ResponseEntity.badRequest().body(exceptionDTO);
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ExceptionDTO> handleValidationException(ValidationException exception) {
    ExceptionDTO exceptionDTO = new ExceptionDTO(
      HttpStatus.BAD_REQUEST.value(),
      HttpStatus.BAD_REQUEST.name(),
      exception.getMessage()
    );
    return ResponseEntity.badRequest().body(exceptionDTO);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ExceptionDTO> handleEntityNotFoundException(EntityNotFoundException exception) {
    ExceptionDTO exceptionDTO = new ExceptionDTO(
      HttpStatus.NOT_FOUND.value(),
      HttpStatus.NOT_FOUND.name(),
      exception.getMessage()
    );
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
  }

  @ExceptionHandler(InsufficientBalanceException.class)
  public ResponseEntity<ExceptionDTO> handleInsufficientBalanceException(InsufficientBalanceException exception) {
    ExceptionDTO exceptionDTO = new ExceptionDTO(
      HttpStatus.BAD_REQUEST.value(),
      HttpStatus.BAD_REQUEST.name(),
      exception.getMessage()
    );
    return ResponseEntity.badRequest().body(exceptionDTO);
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ExceptionDTO> handleBusinessException(BusinessException exception) {
    ExceptionDTO exceptionDTO = new ExceptionDTO(
      HttpStatus.BAD_REQUEST.value(),
      HttpStatus.BAD_REQUEST.name(),
      exception.getMessage()
    );
    return ResponseEntity.badRequest().body(exceptionDTO);
  }

  @ExceptionHandler(UnauthorizedTransactionException.class)
  public ResponseEntity<ExceptionDTO> handleUnauthorizedTransactionException(UnauthorizedTransactionException exception) {
    ExceptionDTO exceptionDTO = new ExceptionDTO(
      HttpStatus.UNAUTHORIZED.value(),
      HttpStatus.UNAUTHORIZED.name(),
      exception.getMessage()
    );
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exceptionDTO);
  }

  @ExceptionHandler(NotificationException.class)
  public ResponseEntity<ExceptionDTO> handleNotificationException(NotificationException exception) {
    ExceptionDTO exceptionDTO = new ExceptionDTO(
      HttpStatus.SERVICE_UNAVAILABLE.value(),
      HttpStatus.SERVICE_UNAVAILABLE.name(),
      exception.getMessage()
    );
    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(exceptionDTO);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
    ExceptionDTO exceptionDTO = new ExceptionDTO(
      HttpStatus.BAD_REQUEST.value(),
      HttpStatus.BAD_REQUEST.name(),
      exception.getBindingResult().getAllErrors().stream().map(objectError -> {
        String name = objectError.getObjectName();
        if (objectError instanceof FieldError) {
          name = ((FieldError) objectError).getField();
        }
        String message = objectError.getDefaultMessage();
        return name + ": " + message;
      }).toList().toString()
    );
    return ResponseEntity.badRequest().body(exceptionDTO);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionDTO> handleOtherException(Exception exception) {
    ExceptionDTO exceptionDTO = new ExceptionDTO(
      HttpStatus.INTERNAL_SERVER_ERROR.value(),
      HttpStatus.INTERNAL_SERVER_ERROR.name(),
      exception.getMessage()
    );
    return ResponseEntity.internalServerError().body(exceptionDTO);
  }

}
