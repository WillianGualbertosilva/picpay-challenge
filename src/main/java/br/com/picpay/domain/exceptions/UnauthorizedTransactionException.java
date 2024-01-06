package br.com.picpay.domain.exceptions;

public class UnauthorizedTransactionException extends BusinessException {

  public UnauthorizedTransactionException(String message) {
    super(message);
  }

}
