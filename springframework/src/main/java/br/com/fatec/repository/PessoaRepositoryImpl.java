package br.com.fatec.repository;

import br.com.fatec.entity.Pessoa;
import br.com.fatec.repository.adapter.PessoaAdapter;
import br.com.fatec.repository.orm.repository.PessoaRepositoryMongo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PessoaRepositoryImpl implements PessoaRepository {
    private final PessoaRepositoryMongo pessoaRepositoryMongo;
    private final PessoaAdapter pessoaAdapter;

    public PessoaRepositoryImpl(PessoaRepositoryMongo pessoaRepositoryMongo, PessoaAdapter pessoaAdapter) {
        this.pessoaRepositoryMongo = pessoaRepositoryMongo;
        this.pessoaAdapter = pessoaAdapter;
    }

    @Override
    public Optional<Pessoa> findById(UUID id) {
        return pessoaRepositoryMongo.findByIdAndAtivoTrue(id).map(pessoaAdapter::toEntity);
    }

    @Override
    public List<Pessoa> findWithPagination(long offset, long limit) {

        Pageable pageable = PageRequest.of((int)(offset / limit), (int)limit);
        return pessoaRepositoryMongo.findByAtivoTrue(pageable).stream()
                .map(pessoaAdapter::toEntity)
                .toList();
    }

    @Override
    public Pessoa save(Pessoa pessoa) {
        var pessoaOrm = pessoaAdapter.toOrm(pessoa);
        var savedPessoaOrm = pessoaRepositoryMongo.save(pessoaOrm);
        return pessoaAdapter.toEntity(savedPessoaOrm);
    }

    @Override
    public void deleteById(UUID id) {
        pessoaRepositoryMongo.deleteById(id);
    }
}
