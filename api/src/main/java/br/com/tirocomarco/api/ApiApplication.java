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

            Arqueiro arq1 = new Arqueiro();
            arq1.setNome("Igor Agiani Silva");
            arq1.setDataNascimento(LocalDate.of(1994, 6, 17));
            arq1.setCategoria("Composto Open Paralimpico");
            arq1.setClube("LA Archery - São Paulo (SP)");

            Arqueiro arq2 = new Arqueiro();
            arq2.setNome("Rodrigo Theodisio Dewes");
            arq2.setDataNascimento(LocalDate.of(1987, 3, 15));
            arq2.setCategoria("Composto Open Paralimpico");
            arq2.setClube("LA Archery - São Paulo (SP)");

            arqueiroRepository.saveAll(Arrays.asList(arq1, arq2));
        };
    }
}