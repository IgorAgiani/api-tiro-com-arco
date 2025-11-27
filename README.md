# Archery Squad Management API

> API RESTful robusta para gestão de atletas de alta performance, desenvolvida com a stack moderna do ecossistema Spring.

## Sobre o Projeto
Como atleta da **Seleção Brasileira de Tiro com Arco**, identifiquei a necessidade de sistemas organizados para gestão de dados esportivos. Este projeto une minha experiência no esporte com a engenharia de software.

Trata-se de uma API Backend completa que gerencia o ciclo de vida dos dados dos arqueiros, garantindo integridade, validação e exposição clara dos recursos através de documentação via Swagger. O foco aqui não é apenas o CRUD, mas a aplicação de **Clean Architecture**, **Verbos HTTP Semânticos** e **Boas Práticas de REST**.

---

## Tecnologias & Arquitetura

* **Core:** Java 21 (LTS) & Spring Boot 3.5.7
* **Arquitetura:** Camadas bem definidas (Controller, Service, Repository, DTOs).
* **Persistência:** Spring Data JPA.
    * *Nota: Utiliza H2 Database (In-Memory) por padrão para facilitar a execução e testes de recrutadores/desenvolvedores, sem necessidade de configuração de ambiente externo.*
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
    git clone [https://github.com/IgorAgiani/api-tiro-com-arco.git](https://github.com/IgorAgiani/api-tiro-com-arco.git)
    cd api-tiro-com-arco
    ```

2.  **Execute via Maven Wrapper (ou Maven local):**
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
- [ ] Migrar banco de dados para **PostgreSQL** via **Docker Compose**.
- [ ] Adicionar testes de integração com **Testcontainers**.
