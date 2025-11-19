package br.com.fatec.controller;

import br.com.fatec.dtos.SalvarPessoaDto;
import br.com.fatec.dtos.PessoaDto;
import br.com.fatec.entity.Pessoa;
import br.com.fatec.services.PessoaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<List<PessoaDto>> obterTodos(
            @RequestParam(defaultValue = "1") int pagina,
            @RequestParam(defaultValue = "10") int itens
    ) {
        List<Pessoa> pessoas = pessoaService.obterPessoasPaginadas(pagina, itens);
        return ResponseEntity.ok(pessoas.stream().map(PessoaDto::fromEntity).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDto> obterPorId(@PathVariable String id) {
        Pessoa pessoa = pessoaService.obterPessoaPorId(UUID.fromString(id));
        return ResponseEntity.ok(PessoaDto.fromEntity(pessoa));
    }

    @PostMapping
    public ResponseEntity<PessoaDto> criar(@RequestBody @Valid  SalvarPessoaDto salvarPessoaDto) {
        Pessoa pessoaCriada = pessoaService.criarPessoa(salvarPessoaDto.nome(), salvarPessoaDto.dataNascimento());
        return ResponseEntity.status(201).body(PessoaDto.fromEntity(pessoaCriada));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDto> atualizar(@PathVariable String id, @RequestBody @Valid  SalvarPessoaDto salvarPessoaDto) {
        Pessoa pessoaAtualizada = pessoaService.atualizarPessoa(UUID.fromString(id), salvarPessoaDto.nome(), salvarPessoaDto.dataNascimento());
        return ResponseEntity.ok(PessoaDto.fromEntity(pessoaAtualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        pessoaService.deletarPessoa(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }
}
