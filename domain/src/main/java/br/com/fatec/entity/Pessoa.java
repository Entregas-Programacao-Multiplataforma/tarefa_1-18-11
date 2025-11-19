package br.com.fatec.entity;

import java.time.LocalDate;
import java.util.UUID;

public record Pessoa(
        UUID id,
        String nome,
        LocalDate dataNascimento,
        Boolean ativo
) {
}
