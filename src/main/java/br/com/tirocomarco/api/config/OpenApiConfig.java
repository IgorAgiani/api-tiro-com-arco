package br.com.tirocomarco.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração para as informações globais da documentação OpenAPI/Swagger.
 */
@Configuration // Indica ao Spring que esta é uma classe de configuração.
public class OpenApiConfig {

    @Bean // Este metodo produz um Bean que o Spring ira gerenciar.
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestão de Arqueiros")
                        .version("v1.0")
                        .description("API REST para o projeto final, permitindo o gerenciamento completo de atletas de tiro com arco.")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}