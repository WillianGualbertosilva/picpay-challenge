package br.com.picpay.api.controllers;

import br.com.picpay.api.dtos.ExceptionDTO;
import br.com.picpay.api.dtos.TransactionDTO;
import br.com.picpay.api.dtos.TransactionResponseDTO;
import br.com.picpay.domain.models.Transaction;
import br.com.picpay.domain.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Transactions")
@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionController {

  @Operation(summary = "Realize transaction", responses = {
    @ApiResponse(responseCode = "200"),
    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ExceptionDTO.class))),
    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ExceptionDTO.class))),
    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ExceptionDTO.class))),
    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ExceptionDTO.class))),
    @ApiResponse(responseCode = "503", content = @Content(schema = @Schema(implementation = ExceptionDTO.class)))
  })
  @PostMapping
  public ResponseEntity<TransactionResponseDTO> create(@RequestBody @Valid TransactionDTO inputDTO) {
    Transaction newTransaction = TransactionDTO.toDomainEntity(inputDTO);
    Transaction transactionCreated = transactionService.create(newTransaction);
    return ResponseEntity.ok(TransactionResponseDTO.toRepresentationModel(transactionCreated));
  }

  private final TransactionService transactionService;

}
