package br.com.tirocomarco.api.service;

import br.com.tirocomarco.api.model.Arqueiro;
import br.com.tirocomarco.api.repository.ArqueiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Indica que esta classe é um serviço do Spring.
public class ArqueiroService {

    @Autowired // Injeção de dependências, cria instância de ArqueiroRepository.
    private ArqueiroRepository arqueiroRepository;

    public List<Arqueiro> listarTodos() {
        return arqueiroRepository.findAll(); // Retorna todos os arqueiros do banco de dados.
    }

    public Arqueiro criar(Arqueiro novoArqueiro) {
        return arqueiroRepository.save(novoArqueiro); // Salva o arqueiro no banco de dados e retorna o objeto salvo.
    }

    public Optional<Arqueiro> buscarPorId(Long id) {
        return arqueiroRepository.findById(id); // Retorna um Optional contendo o arqueiro se encontrado, ou vazio se não encontrado.
    }

    public Optional<Arqueiro> atualizarCompleto(Long id, Arqueiro arqueiroAtualizado) {
        if (!arqueiroRepository.existsById(id)) {
            return Optional.empty();
        }
        arqueiroAtualizado.setId(id);
        return Optional.of(arqueiroRepository.save(arqueiroAtualizado)); // Salva o arqueiro atualizado e retorna um Optional contendo o objeto salvo.
    }

    public boolean deletarPorId(Long id) {
        if (!arqueiroRepository.existsById(id)) {
            return false;
        }
        arqueiroRepository.deleteById(id);
        return true; // Retorna true se o arqueiro foi deletado, false se não foi encontrado.
    }

    public Optional<Arqueiro> atualizarParcial(Long id, Arqueiro dadosParciais) {
        return arqueiroRepository.findById(id)
                .map(arqueiroExistente -> {
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
                    return arqueiroRepository.save(arqueiroExistente);
                }); // Retorna um Optional contendo o arqueiro atualizado ou vazio se não encontrado.
    }
}
