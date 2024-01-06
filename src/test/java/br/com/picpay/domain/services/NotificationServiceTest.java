package br.com.picpay.domain.services;

import br.com.picpay.domain.exceptions.NotificationException;
import br.com.picpay.domain.models.User;
import br.com.picpay.domain.models.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

public class NotificationServiceTest {

  @Test
  public void shouldNotThrowAExceptionWhenSendWithOkValues() {
    User user = getValidUser();

    NotificationService notificationService = new NotificationService();
    Assertions.assertDoesNotThrow(() -> notificationService.send(user, "Transação realizada com sucesso"));
  }

  @Test
  public void shouldThrowNotificationExceptionWhenSendWithErrorValues() {
    User user = getValidUser();

    NotificationService notificationService = new NotificationService();
    Assertions.assertThrows(NotificationException.class, () -> notificationService.send(user, "Sucesso"));
  }

  private User getValidUser() {
    User user = new User();
    user.setId(UUID.randomUUID());
    user.setFirstName("John");
    user.setLastName("Doe");
    user.setDocument("123456789-01");
    user.setEmail("johndoe@email.com");
    user.setPassword("123456");
    user.setBalance(new BigDecimal("100"));
    user.setUserType(UserType.COMMON);
    return user;
  }
}
