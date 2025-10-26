package br.com.tirocomarco.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data // Lombok: Gera Getters, Setters, toString, equals e hashCode.
@NoArgsConstructor // Lombok: Gera um construtor sem argumentos (obrigatório para o JPA).
@Entity // JPA: Indica que esta classe é uma entidade persistente.
public class Arqueiro {

    @Id // JPA: Marca o campo 'id' como a chave primária da tabela.
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id; // JPA: Configura o ID para ser gerado automaticamente pelo banco (auto-incremento).
    private String nome;
    private LocalDate dataNascimento;
    private String categoria;
    private String clube;

}
