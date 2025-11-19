package br.com.fatec.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SalvarPessoaDto(
        @NotNull
        @NotBlank
        String nome,
        @NotNull
        LocalDate dataNascimento
) {
}
