package br.com.fatec.repository.adapter;

import br.com.fatec.entity.Pessoa;
import br.com.fatec.repository.orm.model.PessoaOrm;
import org.springframework.stereotype.Service;

@Service
public class PessoaAdapter {
    public Pessoa toEntity(PessoaOrm pessoaOrm) {
        return new Pessoa(
                pessoaOrm.id(),
                pessoaOrm.nome(),
                pessoaOrm.dataNascimento(),
                pessoaOrm.ativo()
        );
    }

    public PessoaOrm toOrm(Pessoa pessoa) {
        return new PessoaOrm(
                pessoa.id(),
                pessoa.nome(),
                pessoa.dataNascimento(),
                pessoa.ativo()
        );
    }
}
