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

    @GetMapping("{id}") // Mapeia requisições GET que contenham um ID na URL
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

    @PutMapping("{id}") // Mapeia requisições PUT que contenham um ID na URL.
    public ResponseEntity<Arqueiro> atualizarArqueiro(@PathVariable Long id, @RequestBody Arqueiro arqueiroAtualizado) {
        if (!arqueiroRepository.existsById(id)) { // Verifica se o arqueiro com o ID fornecido existe.
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found se o arqueiro não existir.
        }

        arqueiroAtualizado.setId(id); // Garante que o ID do arqueiro atualizado seja o mesmo.

        Arqueiro salvo = arqueiroRepository.save(arqueiroAtualizado); // Salva as alterações no banco de dados.
        return ResponseEntity.ok(salvo); // Retorna 200 OK com o arqueiro atualizado.
    }

    @DeleteMapping("{id}") // Mapeia requisições DELETE que contenham um ID na URL.
    public ResponseEntity<Void> deletarArqueiro(@PathVariable Long id) {
        if (!arqueiroRepository.existsById(id)) { // Verifica se o arqueiro com o ID fornecido existe.
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found se o arqueiro não existir.
        }
        arqueiroRepository.deleteById(id); // Deleta o arqueiro do banco de dados.
        return ResponseEntity.noContent().build(); // Retorna 204 No Content para indicar que a deleção foi bem-sucedida.
    }

    @PatchMapping("{id}") // Mapeia requisições PATCH que contenham um ID na URL.
    public ResponseEntity<Arqueiro> atualizarParcial(@PathVariable Long id, @RequestBody Arqueiro dadosParciais) {
        // Usa o findById para buscar o arqueiro existente e retorna um Optional.
        return arqueiroRepository.findById(id).map(arqueiroExistente -> {

            // Verifica cada campo dos dados recebidos. Se não for nulo, atualiza o campo correspondente.
            if (dadosParciais.getNome() != null) {
                arqueiroExistente.setNome(dadosParciais.getNome());
            }
            if (dadosParciais.getDataNascimento() != null) {
                arqueiroExistente.setDataNascimento(dadosParciais.getDataNascimento());
            }
            if (dadosParciais.getCategoria() != null) {
                arqueiroExistente.setCategoria(dadosParciais.getCategoria());
            }
            if (dadosParciais.getClube() != null) {
                arqueiroExistente.setClube(dadosParciais.getClube());
            }

            // Salva o arqueiro com os campos atualizados.
            Arqueiro arqueiroAtualizado = arqueiroRepository.save(arqueiroExistente);
            return ResponseEntity.ok(arqueiroAtualizado);

        }).orElse(ResponseEntity.notFound().build()); // Se o findById não encontrar nada, retorna 404.
    }
}
