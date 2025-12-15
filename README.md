# Gestão de Arqueiros API

> API RESTful robusta para gestão de atletas, desenvolvida com o ecossistema Spring.

## Sobre o Projeto
Para fugir dos exemplos genéricos (como "To-do List"), utilizei minha vivência como ex Atleta da Seleção Brasileira de Tiro com Arco como domínio do problema. O desafio proposto foi criar um backend para gerenciar atletas, simulando um cenário real onde a integridade dos dados e a clareza da API são prioritárias.

Trata-se de uma API Backend completa que gerencia os dados dos arqueiros, garantindo integridade, validação e exposição clara dos recursos através de documentação via Swagger. O foco aqui não é apenas o CRUD, mas a aplicação de **Clean Architecture**, **Verbos HTTP Semânticos** e **Boas Práticas de REST**.

---

## Tecnologias & Arquitetura

* **Core:** Java 21 (LTS) & Spring Boot 3.5.7
* **Arquitetura:** Camadas bem definidas (Controller, Service, Repository, DTOs).
* **Persistência:** PostgreSQL via Docker Compose.
* **Validação:** Hibernate Validator (Bean Validation).
* **Documentação:** OpenAPI 3.0 (Swagger UI).
* **Produtividade:** Project Lombok para redução de boilerplate.
* **Build:** Maven.

---

## Funcionalidades (Endpoints)

A API segue estritamente o modelo de maturidade de Richardson nível 2:

| Método | Endpoint | Descrição | Status Sucesso |
| :--- | :--- | :--- | :--- |
| `GET` | `/arqueiros` | Lista todos os atletas ativos. | `200 OK` |
| `GET` | `/arqueiros/{id}` | Busca detalhada de um atleta. | `200 OK` |
| `POST` | `/arqueiros` | Cria novo registro com validação de campos. | `201 Created` |
| `PUT` | `/arqueiros/{id}` | Atualização completa do recurso. | `200 OK` |
| `DELETE` | `/arqueiros/{id}` | Remoção de registro da base. | `204 No Content` |

---

## Como Rodar Localmente

A aplicação foi desenhada para ser "Plug & Play".

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/IgorAgiani/api-tiro-com-arco.git
    ```

2. **Suba a Infraestrutura (Banco de Dados):**
   ```bash
   docker-compose up -d
   ```

3.  **Inicie a Aplicação:**
    ```bash
    mvn spring-boot:run
    ```

3.  **Acesse a Documentação (Swagger):**
    Abra o navegador em: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
    *Lá você poderá testar todas as requisições diretamente pela interface visual.*

---

## Próximos Passos (Roadmap)
Para evoluir esta aplicação para um ambiente de produção real (Cloud), os seguintes passos estão mapeados:
- [ ] Implementar **Spring Security** (OAuth2/JWT) para proteção dos endpoints.
- [ ] Adicionar testes de integração com **Testcontainers**.
