package br.com.picpay.domain.services;

import br.com.picpay.domain.exceptions.*;
import br.com.picpay.domain.models.User;
import br.com.picpay.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

  private final UserRepository userRepository;

  public void validateUserTypeAndBalance(User sender, BigDecimal amount) {
    if (sender.isMerchant()) {
      throw new BusinessException("Usuário do tipo lojista não está autorizado a fazer transações");
    }
    sender.validateBalance(amount);
  }

  public User findById(UUID id) {
    return userRepository.findById(id).orElseThrow(() ->
      new EntityNotFoundException("Usuário não encontrado com o id informado"));
  }

  public User create(User user) {
    try {
      user.validate();
    } catch (BusinessException e) {
      throw new ValidationException(e.getMessage());
    }

    Optional<User> existsUser = userRepository.findUserByDocument(user.getDocument());
    if (existsUser.isPresent()) {
      throw new UserAlreadyExistsException("Já existe usuário com o documento informado");
    }
    existsUser = userRepository.findUserByEmail(user.getEmail());
    if (existsUser.isPresent()) {
      throw new UserAlreadyExistsException("Já existe usuário com o email informado");
    }

    return userRepository.save(user);
  }

  public User update(User user) {
    try {
      user.validate();
    } catch (BusinessException e) {
      throw new ValidationException(e.getMessage());
    }

    return userRepository.save(user);
  }

  public List<User> getAll() {
    return userRepository.findAll();
  }

}
