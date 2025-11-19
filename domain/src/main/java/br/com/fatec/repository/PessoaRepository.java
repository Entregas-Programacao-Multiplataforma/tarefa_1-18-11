package br.com.fatec.repository;

import br.com.fatec.entity.Pessoa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PessoaRepository {
    Optional<Pessoa> findById(UUID id);
    List<Pessoa> findWithPagination(long offset, long limit);
    Pessoa save(Pessoa pessoa);
    void deleteById(UUID id);
}
