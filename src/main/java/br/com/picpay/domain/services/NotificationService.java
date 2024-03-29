package br.com.picpay.domain.services;

import br.com.picpay.api.dtos.NotificationDTO;
import br.com.picpay.domain.exceptions.NotificationException;
import br.com.picpay.domain.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NotificationService {

//  private final RestTemplate restTemplate;

  public void send(User user, String message) {
    String url = "http://o4d9z.mocklab.io/notify";
    String email = user.getEmail();

    NotificationDTO notificationRequest = new NotificationDTO(email, message);

//    ResponseEntity<String> notificationResponse = restTemplate.postForEntity(url, notificationRequest, String.class);
    ResponseEntity<String> notificationResponse;
    if (message.contains("Transação")) {
      notificationResponse = ResponseEntity.ok().build();
    } else {
      notificationResponse = ResponseEntity.badRequest().build();
    }

    if (!(notificationResponse.getStatusCode() == HttpStatus.OK)) {
      System.out.println("Erro ao enviar notificação");
      throw new NotificationException("Serviço de notificação está fora do ar");
    }

    System.out.println("Notificação enviada para o usuário: " + user.getFullName());
  }

}
