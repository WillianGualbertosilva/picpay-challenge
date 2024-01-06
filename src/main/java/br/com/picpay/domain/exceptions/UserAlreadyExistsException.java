package br.com.picpay.domain.exceptions;

public class UserAlreadyExistsException extends BusinessException {

  public UserAlreadyExistsException(String message) {
    super(message);
  }

}
