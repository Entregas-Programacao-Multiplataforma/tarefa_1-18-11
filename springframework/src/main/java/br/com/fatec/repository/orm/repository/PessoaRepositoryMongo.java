package br.com.fatec.repository.orm.repository;

import br.com.fatec.repository.orm.model.PessoaOrm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface PessoaRepositoryMongo extends MongoRepository<PessoaOrm, UUID> {
    Page<PessoaOrm> findByAtivoTrue(Pageable pageable);
    Optional<PessoaOrm> findByIdAndAtivoTrue(UUID id);
}
