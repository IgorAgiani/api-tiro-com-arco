package br.com.tirocomarco.api;

import br.com.tirocomarco.api.model.Arqueiro;
import br.com.tirocomarco.api.repository.ArqueiroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Bean // carrega os dados iniciais de teste no banco H2 toda vez que a aplicação iniciar.
    CommandLineRunner initDatabase(ArqueiroRepository arqueiroRepository) {
        return args -> {

            arqueiroRepository.deleteAll(); // Limpa o banco antes de inserir para evitar duplicatas a cada reinicialização.

            Arqueiro arq1 = new Arqueiro(
                    null,
                    "Igor Agiani Silva",
                    LocalDate.of(1994, 6, 17),
                    "Composto Open Paralimpico",
                    "LA Archery - São Paulo (SP)");

            Arqueiro arq2 = new Arqueiro(null,
                    "Rodrigo Theodisio Dewes",
                    LocalDate.of(1987, 3, 15),
                    "Composto Open Paralimpico",
                    "LA Archery - São Paulo (SP)");

            arqueiroRepository.saveAll(Arrays.asList(arq1, arq2));
        };
    }
}