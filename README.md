# Módulo 1 - CRUD de Pessoas com Spring Boot e Graylog

Este projeto faz parte do trabalho final da FATEC Multiplataforma e tem como objetivo implementar um CRUD de pessoas utilizando arquitetura em módulos, monitoramento de logs com Graylog e persistência de dados.

## Descrição do Projeto

O sistema é composto por dois módulos principais:

- `domain`: contém as regras de negócio, entidades, exceções, serviços e repositórios da aplicação.
- `springframework`: módulo responsável pela API REST utilizando Spring Boot, configuração dos beans, controllers, DTOs, adapters e integração com o mundo externo.

A aplicação expõe uma API para cadastro, consulta, atualização e exclusão de pessoas, persistindo os dados em um repositório, e integrando com ferramentas de observabilidade via logs estruturados.

## Motivos da Implementação

A implementação foi feita seguindo os seguintes objetivos principais:

- **Separação de responsabilidades**: Isolar a lógica de negócio (`domain`) da camada de infraestrutura e exposição (`springframework`), facilitando manutenção, testes e eventual reutilização do domínio em outros projetos.
- **Boas práticas com Spring Boot**: Utilizar injeção de dependência, camadas bem definidas (controller, service, repository), tratamento centralizado de erros e DTOs para comunicação externa.
- **Observabilidade e monitoramento**: Configurar o Graylog (via Docker Compose) para centralizar e analisar logs da aplicação, permitindo acompanhamento de erros, desempenho e auditoria das operações realizadas.
- **Uso de containers**: Facilitar a configuração de ambiente (MongoDB, OpenSearch e Graylog) utilizando Docker, tornando o projeto mais simples de executar em diferentes máquinas.
- **Base para projetos futuros**: Servir como base de estudo e referência para arquitetura modular em Java com Spring Boot, permitindo evolução para outras funcionalidades e integrações.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Maven
- Docker e Docker Compose
- Graylog
- MongoDB
- OpenSearch

## Execução do Projeto

1. Suba os containers definidos no `docker-compose.yaml` (MongoDB, OpenSearch e Graylog).
2. Na raiz do projeto, execute o build e rode o módulo `springframework` com Maven:
   - Build do projeto completo: `./mvnw clean install`
   - Rodar apenas o módulo `springframework`: `./mvnw -pl springframework -am spring-boot:run`
3. Acesse o Graylog em `http://localhost:9000` com usuário admin e senha "senha123" para acompanhar os logs gerados pela aplicação.
