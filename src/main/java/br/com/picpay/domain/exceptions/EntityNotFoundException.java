package br.com.picpay.domain.exceptions;

public class EntityNotFoundException extends BusinessException {

  public EntityNotFoundException(String message) {
    super(message);
  }

}
