package br.com.tirocomarco.api.controller;

import br.com.tirocomarco.api.model.Arqueiro;
import br.com.tirocomarco.api.service.ArqueiroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController // Combina @Controller e @ResponseBody, indicando que esta classe retornará JSON.
@RequestMapping("/arqueiros") // Define a URL base para todos os métodos da// classe.
public class ArqueiroController {

    @Autowired // Injeção de dependências, cria instância de ArqueiroService.
    private ArqueiroService arqueiroService;

    @GetMapping // Mapeia requisições GET.
    public List<Arqueiro> listarTodos() {
        return arqueiroService.listarTodos();
    }

    @GetMapping("/{id}")// Mapeia requisições GET que contenham um ID na URL.
    public ResponseEntity<Arqueiro> buscarPorId(@PathVariable Long id) {
        return arqueiroService.buscarPorId(id)
                .map(arqueiro -> ResponseEntity.ok(arqueiro)) // Se o Optional contiver um arqueiro, retorna 200 OK com ele.
                .orElse(ResponseEntity.notFound().build());    // Se o Optional estiver vazio, retorna 404 Not Found.
    }

    @PostMapping // Mapeia requisições POST.
    public ResponseEntity<Arqueiro> criar(@RequestBody Arqueiro novoArqueiro) {
        Arqueiro arqueiroSalvo = arqueiroService.criar(novoArqueiro);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(arqueiroSalvo.getId())
                .toUri();

        return ResponseEntity.created(location).body(arqueiroSalvo);
    }

    @PutMapping("/{id}") // Mapeia requisições PUT que contenham um ID na URL.
    public ResponseEntity<Arqueiro> atualizarCompleto(@PathVariable Long id, @RequestBody Arqueiro arqueiroAtualizado) {
        return arqueiroService.atualizarCompleto(id, arqueiroAtualizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") // Mapeia requisições DELETE que contenham um ID na URL.
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        boolean deletado = arqueiroService.deletarPorId(id);
        if (deletado) {
            return ResponseEntity.noContent().build(); // Se o service retornou true, retorna 204.
        } else {
            return ResponseEntity.notFound().build();  // Se retornou false, retorna 404.
        }
    }

    @PatchMapping("/{id}") // Mapeia requisições PATCH que contenham um ID na URL.
    public ResponseEntity<Arqueiro> atualizarParcial(@PathVariable Long id, @RequestBody Arqueiro dadosParciais) {
        return arqueiroService.atualizarParcial(id, dadosParciais)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
