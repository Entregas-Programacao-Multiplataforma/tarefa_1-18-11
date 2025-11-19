package br.com.fatec.services;

import br.com.fatec.entity.Pessoa;
import br.com.fatec.exceptions.NascimentoInvalidoException;
import br.com.fatec.exceptions.PessoaNotFoundException;
import br.com.fatec.repository.PessoaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class PessoaService {
    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa obterPessoaPorId(UUID id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNotFoundException("Pessoa com ID " + id.toString() + " não encontrada"));
    }

    public List<Pessoa> obterPessoasPaginadas(long pagina, long tamanho) {
        long offset = (pagina - 1) * tamanho;
        return pessoaRepository.findWithPagination(offset, tamanho);
    }

    public Pessoa criarPessoa(String nome, LocalDate dataNascimento) {
        if(dataNascimento.isAfter(LocalDate.now())) {
            throw new NascimentoInvalidoException("Data de nascimento não pode ser no futuro");
        }
        Pessoa pessoa = new Pessoa(UUID.randomUUID(), nome, dataNascimento, true);
        return pessoaRepository.save(pessoa);
    }

    public Pessoa atualizarPessoa(UUID id, String nome, LocalDate dataNascimento) {
        if(dataNascimento.isAfter(LocalDate.now())) {
            throw new NascimentoInvalidoException("Data de nascimento não pode ser no futuro");
        }

        Pessoa pessoaExistente = pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNotFoundException("Pessoa com ID " + id.toString() + " não encontrada"));

        Pessoa pessoaAtualizada = new Pessoa(
                pessoaExistente.id(),
                nome,
                dataNascimento,
                pessoaExistente.ativo()
        );

        return pessoaRepository.save(pessoaAtualizada);
    }

    public void deletarPessoa(UUID id) {
        Pessoa pessoaExistente = pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNotFoundException("Pessoa com ID " + id.toString() + " não encontrada"));

        pessoaRepository.deleteById(pessoaExistente.id());
    }
}
