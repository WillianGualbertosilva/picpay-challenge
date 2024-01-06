package br.com.picpay.domain.exceptions;

public class InsufficientBalanceException extends BusinessException {

  public InsufficientBalanceException(String message) {
    super(message);
  }

}
