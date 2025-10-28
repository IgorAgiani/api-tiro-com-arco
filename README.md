# API de Gestão de Arqueiros 

Este projeto é uma API REST para o gerenciamento de atletas de tiro com arco, desenvolvido como um projeto de estudo para a construção de APIs com Spring Boot. A aplicação implementa um CRUD completo para a entidade `Arqueiro`, seguindo as melhores práticas de arquitetura em camadas (Controller, Service, Repository).

---
## Funcionalidades

A API oferece um conjunto completo de operações CRUD para a entidade `Arqueiro`:

- ✅ **Listagem de todos os arqueiros:** `GET /arqueiros`
- ✅ **Busca de um arqueiro por ID:** `GET /arqueiros/{id}`
- ✅ **Cadastro de um novo arqueiro:** `POST /arqueiros`
    - Inclui validação de dados para garantir a integridade das informações.
    - Retorna o status `201 Created` com a localização do novo recurso.
- ✅ **Atualização completa de um arqueiro:** `PUT /arqueiros/{id}`
- ✅ **Atualização parcial de um arqueiro:** `PATCH /arqueiros/{id}`
- ✅ **Deleção de um arqueiro:** `DELETE /arqueiros/{id}`
- ✅ **Documentação interativa da API:** Acesso via Swagger UI.

---

## Tecnologias Utilizadas

Este projeto foi construído utilizando as seguintes tecnologias e bibliotecas:

* **Linguagem:** Java 21
* **Framework:** Spring Boot 3.5.7
* **Persistência de Dados:** Spring Data JPA
* **Banco de Dados:** H2 Database (em memória)
* **Gerenciamento de Dependências:** Maven
* **Documentação:** Springdoc OpenAPI (Swagger UI)
* **Qualidade de Código:** Project Lombok

---

## Como Executar o Projeto

Para executar este projeto localmente, siga os passos abaixo.

**Pré-requisitos:**
* JDK 21 ou superior instalado.
* Apache Maven 3.8 ou superior instalado.

1.  **Clone o repositório:**
    ```sh
    git clone https://github.com/IgorAgiani/api-tiro-com-arco.git
    ```

2.  **Navegue até a pasta raiz do projeto:**
    ```sh
    cd api-tiro-com-arco 
    ```

3.  **Execute a aplicação utilizando o Maven:**
    ```sh
    mvn spring-boot:run
    ```

4.  A aplicação iniciará e estará disponível em `http://localhost:8080`.

---

## Documentação e Testes

A documentação completa e interativa dos endpoints foi gerada com o Swagger UI e pode ser acessada no seguinte endereço após a inicialização da aplicação:

[**http://localhost:8080/swagger-ui.html**](http://localhost:8080/swagger-ui.html)

Através desta interface, é possível visualizar todos os detalhes dos endpoints e testar cada uma das operações diretamente pelo navegador.