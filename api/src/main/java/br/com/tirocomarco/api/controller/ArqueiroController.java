package br.com.tirocomarco.api.controller;

import br.com.tirocomarco.api.model.Arqueiro;
import br.com.tirocomarco.api.repository.ArqueiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Combina @Controller e @ResponseBody, indicando que esta classe retornará JSON.
@RequestMapping("/arqueiros") // Define a URL base para todos os métodos da// classe.
public class ArqueiroController {

    @Autowired // Injeção de dependências, cria instância de ArqueiroRepository.
    private ArqueiroRepository arqueiroRepository;

    @GetMapping // Mapeia requisições GET.
    public List<Arqueiro> listarTodos() {
        return arqueiroRepository.findAll(); // Retorna todos os arqueiros do banco de dados.
    }
}
