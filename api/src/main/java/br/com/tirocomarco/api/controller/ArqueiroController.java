package br.com.tirocomarco.api.controller;

import br.com.tirocomarco.api.model.Arqueiro;
import br.com.tirocomarco.api.repository.ArqueiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("{id}") // Mapeie requisição para uma parte variável da URL.
    public ResponseEntity<Arqueiro> buscarPorId(@PathVariable Long id) {
        // ResponseEntity  para melhor controle total sobre a resposta HTTP
        // @PathVariable, extrai o ID da URL.
        Optional<Arqueiro> arqueiroEncontrado = arqueiroRepository.findById(id); // Retorna o arqueiro com o ID fornecido.
        if (arqueiroEncontrado.isPresent()) {
            // Se o arqueiro foi encontrado, retorna 200 OK com o arqueiro no corpo.
            return ResponseEntity.ok(arqueiroEncontrado.get());
        } else {
            // Se não foi encontrado, retorna 404 Not Found sem corpo.
            return ResponseEntity.notFound().build();
        }
    }
}
