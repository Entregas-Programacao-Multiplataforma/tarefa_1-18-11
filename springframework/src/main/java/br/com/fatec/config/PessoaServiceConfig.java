package br.com.fatec.config;

import br.com.fatec.repository.PessoaRepository;
import br.com.fatec.services.PessoaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PessoaServiceConfig {
    @Bean
    public PessoaService pessoaService(PessoaRepository pessoaRepository) {
        return new PessoaService(pessoaRepository);
    }
}
