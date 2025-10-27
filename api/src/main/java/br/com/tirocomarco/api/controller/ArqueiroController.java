package br.com.tirocomarco.api.controller;

import br.com.tirocomarco.api.model.Arqueiro;
import br.com.tirocomarco.api.repository.ArqueiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping // Mapeia requisições POST.
    public Arqueiro criarArqueiro(@RequestBody Arqueiro arqueiro) { // @RequestBody, converte o JSON do corpo da requisição em um objeto Arqueiro.
        return arqueiroRepository.save(arqueiro); // Salva um novo arqueiro no banco de dados.
    }
}
