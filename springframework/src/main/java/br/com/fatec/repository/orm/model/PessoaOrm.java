package br.com.fatec.repository.orm.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document(value = "pessoa")
public record PessoaOrm(
        @Id
        UUID id,
        String nome,
        LocalDate dataNascimento,
        Boolean ativo
) {
}
