package br.com.picpay.api.dtos;

import br.com.picpay.core.validation.EnumValid;
import br.com.picpay.domain.models.User;
import br.com.picpay.domain.models.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UserRequestDTO(
  @Schema(example = "John")
  @NotBlank
  String firstName,
  @Schema(example = "Doe")
  @NotBlank
  String lastName,
  @Schema(example = "123456789-01")
  @NotBlank
  String document,
  @Schema(example = "johndoe@email.com")
  @NotBlank @Email
  String email,
  @Schema(example = "abc123")
  @NotBlank @Size(min = 6)
  String password,
  @Schema(example = "COMMON")
  @EnumValid(enumClass = UserType.class)
  String userType,
  @Schema(example = "3000.67")
  @NotNull @PositiveOrZero
  BigDecimal balance
) {

  public static User toDomainEntity(UserRequestDTO dto) {
    User domainEntity = new User();
    domainEntity.setFirstName(dto.firstName());
    domainEntity.setLastName(dto.lastName());
    domainEntity.setDocument(dto.document());
    domainEntity.setEmail(dto.email());
    domainEntity.setPassword(dto.password());
    domainEntity.setUserType(UserType.valueOf(dto.userType()));
    domainEntity.setBalance(dto.balance());

    return domainEntity;
  }
}
