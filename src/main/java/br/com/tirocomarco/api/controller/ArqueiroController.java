package br.com.tirocomarco.api.controller;

import br.com.tirocomarco.api.model.Arqueiro;
import br.com.tirocomarco.api.service.ArqueiroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController // Combina @Controller e @ResponseBody, indicando que esta classe retornará JSON.
@RequestMapping("/arqueiros") // Define a URL base para todos os métodos da classe.
public class ArqueiroController {

    @Autowired // Injeção de dependências, cria instância de ArqueiroService.
    private ArqueiroService arqueiroService;

    @Operation(summary = "Lista todos os arqueiros", description = "Retorna uma lista com todos os arqueiros cadastrados no banco de dados.") // Descreve a operação
    @ApiResponse(responseCode = "200", description = "Listagem realizada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Arqueiro.class))) // Descreve a resposta 200 OK.
    @GetMapping // Mapeia requisições GET.
    public List<Arqueiro> listarTodos() {
        return arqueiroService.listarTodos();
    }

    @Operation(summary = "Busca um arqueiro por ID", description = "Retorna os dados de um único arqueiro com base no seu ID.")
    @ApiResponse(responseCode = "200", description = "Arqueiro encontrado com sucesso") // Descreve a resposta 200 OK.
    @ApiResponse(responseCode = "404", description = "Arqueiro não encontrado para o ID informado", content = @Content) // Descreve a resposta 404 Not Found.
    @GetMapping("/{id}")// Mapeia requisições GET que contenham um ID na URL.
    public ResponseEntity<Arqueiro> buscarPorId(@PathVariable Long id) {
        return arqueiroService.buscarPorId(id)
                .map(arqueiro -> ResponseEntity.ok(arqueiro)) // Se o Optional contiver um arqueiro, retorna 200 OK com ele.
                .orElse(ResponseEntity.notFound().build());    // Se o Optional estiver vazio, retorna 404 Not Found.
    }
    
    @Operation(summary = "Cria um novo arqueiro", description = "Cria um novo arqueiro no sistema, com todos os campos são obrigatórios.")
    @ApiResponse(responseCode = "201", description = "Arqueiro criado com sucesso", content = @Content(schema = @Schema(implementation = Arqueiro.class))) // Descreve a resposta 201 Created.
    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos no corpo da requisição", content = @Content) // Descreve a resposta 400 Bad Request.
    @PostMapping // Mapeia requisições POST.
    @Valid // Valida o corpo da requisição.
    public ResponseEntity<Arqueiro> criar(@RequestBody Arqueiro novoArqueiro) {
        Arqueiro arqueiroSalvo = arqueiroService.criar(novoArqueiro);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(arqueiroSalvo.getId())
                .toUri();

        return ResponseEntity.created(location).body(arqueiroSalvo);
    }

    @Operation(summary = "Atualiza um arqueiro por completo", description = "Substitui todos os dados de um arqueiro existente.")
    @ApiResponse(responseCode = "200", description = "Arqueiro atualizado com sucesso") // Descreve a resposta 200 OK.
    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos") // Descreve a resposta 400 Bad Request.
    @ApiResponse(responseCode = "404", description = "Arqueiro não encontrado") // Descreve a resposta 404 Not Found.
    @PutMapping("/{id}") // Mapeia requisições PUT que contenham um ID na URL.
    @Valid // Valida o corpo da requisição.
    public ResponseEntity<Arqueiro> atualizarCompleto(@PathVariable Long id, @RequestBody Arqueiro arqueiroAtualizado) {
        return arqueiroService.atualizarCompleto(id, arqueiroAtualizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deleta um arqueiro por ID", description = "Remove permanentemente um arqueiro do sistema.")
    @ApiResponse(responseCode = "204", description = "Arqueiro deletado com sucesso") // Descreve a resposta 204 No Content.
    @ApiResponse(responseCode = "404", description = "Arqueiro não encontrado") // Descreve a resposta 404 Not Found.
    @DeleteMapping("/{id}") // Mapeia requisições DELETE que contenham um ID na URL.
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        boolean deletado = arqueiroService.deletarPorId(id);
        if (deletado) {
            return ResponseEntity.noContent().build(); // Se retornou true, retorna 204 No Content.
        } else {
            return ResponseEntity.notFound().build(); // Se retornou false, retorna 404 Not Found.
        }
    }

    @Operation(summary = "Atualiza um arqueiro parcialmente", description = "Atualiza um ou mais campos de um arqueiro existente, apenas os campos enviados no corpo da requisição serão alterados.")
    @ApiResponse(responseCode = "200", description = "Arqueiro atualizado com sucesso") // Descreve a resposta de sucesso.
    @ApiResponse(responseCode = "404", description = "Arqueiro não encontrado") // Descreve a resposta de erro.
    @PatchMapping("/{id}") // Mapeia requisições PATCH que contenham um ID na URL.
    public ResponseEntity<Arqueiro> atualizarParcial(@PathVariable Long id, @RequestBody Arqueiro dadosParciais) {
        return arqueiroService.atualizarParcial(id, dadosParciais)
                .map(ResponseEntity::ok) // Retorna 200 OK com o arqueiro atualizado.
                .orElse(ResponseEntity.notFound().build()); // Retorna 404 Not Found se o arqueiro não existir.
    }
}
