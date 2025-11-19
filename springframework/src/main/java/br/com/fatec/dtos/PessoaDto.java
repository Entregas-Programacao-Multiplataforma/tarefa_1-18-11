package br.com.fatec.dtos;

import br.com.fatec.entity.Pessoa;

import java.time.LocalDate;
import java.util.UUID;

public record PessoaDto(
        UUID id,
        String nome,
        LocalDate dataNascimento
) {
    public static PessoaDto fromEntity(Pessoa pessoa) {
        return new PessoaDto(
                pessoa.id(),
                pessoa.nome(),
                pessoa.dataNascimento()
        );
    }
}
