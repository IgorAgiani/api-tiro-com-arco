package br.com.tirocomarco.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data // Lombok: Gera Getters, Setters, toString, equals e hashCode.
@NoArgsConstructor // Gera um construtor sem argumentos.
@AllArgsConstructor // Gera um construtor com todos os argumentos.
@Entity // Indica que esta classe é uma entidade persistente.
public class Arqueiro {

    @Id // Marca o campo 'id' como a chave primária da tabela.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define a estratégia de geração automática do ID.
    @Schema(description = "Identificador único do arqueiro, gerado automaticamente.") // Documentação Swagger, para o campo 'id'.
    private Long id;

    @NotBlank(message = "O nome não pode estar em branco.") // Validação, o campo 'nome' não pode ser nulo ou vazio.
    @Schema(description = "Nome completo do atleta.", example = "Igor Agiani Silva", requiredMode = Schema.RequiredMode.REQUIRED) // Documentação Swagger, para o campo 'nome'.
    private String nome;

    @NotNull(message = "A data de nascimento não pode ser nula.") // Validação, o campo 'dataNascimento' não pode ser nulo.
    @Past(message = "A data de nascimento deve ser uma data no passado.") // Validação, o campo 'dataNascimento' deve ser uma data passada.
    @Schema(description = "Data de nascimento do atleta no formato AAAA-MM-DD.", example = "1994-06-17", requiredMode = Schema.RequiredMode.REQUIRED) // Documentação Swagger, para o campo 'dataNascimento'.
    private LocalDate dataNascimento;

    @NotBlank(message = "A categoria não pode estar em branco.") // Validação, o campo 'categoria' não pode ser nulo ou vazio.
    @Schema(description = "Principal categoria ou modalidade praticada pelo atleta.", example = "Composto Open Paralimpico", requiredMode = Schema.RequiredMode.REQUIRED) // Documentação Swagger, para o campo 'categoria'.
    private String categoria;

    @NotBlank(message = "O clube não pode estar em branco.") // Validação, o campo 'clube' não pode ser nulo ou vazio.
    @Schema(description = "Clube ao qual o atleta é filiado.", example = "LA Archery - São Paulo (SP)", requiredMode = Schema.RequiredMode.REQUIRED) // Documentação Swagger, para o campo 'clube'.
    private String clube;
}

