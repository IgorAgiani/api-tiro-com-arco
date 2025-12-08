package br.com.tirocomarco.api.service;

import br.com.tirocomarco.api.model.Arqueiro;
import br.com.tirocomarco.api.repository.ArqueiroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service // Indica que esta classe é um serviço do Spring.
public class ArqueiroService {

    // LOGS: Adicionado para rastrear o que acontece no sistema (Info, Erros, Alertas).
    private static final Logger logger = LoggerFactory.getLogger(ArqueiroService.class);

    @Autowired // Injeção de dependências, cria instância de ArqueiroRepository.
    private ArqueiroRepository arqueiroRepository;

    public List<Arqueiro> listarTodos() {
        logger.info("Listando todos os arqueiros da base de dados");
        return arqueiroRepository.findAll(); // Retorna todos os arqueiros do banco de dados.
    }

    @Transactional // Garante integridade: Se der erro, desfaz tudo (Rollback).
    public Arqueiro criar(Arqueiro novoArqueiro) {
        logger.info("Iniciando criação do arqueiro: {}", novoArqueiro.getNome());
        try {
            Arqueiro salvo = arqueiroRepository.save(novoArqueiro); // Salva o arqueiro no banco de dados e retorna o objeto salvo.
            logger.info("Arqueiro criado com sucesso. ID: {}", salvo.getId());
            return salvo;
        } catch (Exception e) {
            logger.error("Erro ao salvar arqueiro: {}", novoArqueiro.getNome(), e);
            throw e; // Relança o erro para o Controller tratar.
        }
    }

    public Optional<Arqueiro> buscarPorId(Long id) {
        logger.info("Buscando arqueiro pelo ID: {}", id);
        return arqueiroRepository.findById(id); // Retorna um Optional contendo o arqueiro se encontrado, ou vazio se não encontrado.
    }

    @Transactional
    public Optional<Arqueiro> atualizarCompleto(Long id, Arqueiro arqueiroAtualizado) {
        logger.info("Tentando atualização completa do arqueiro ID: {}", id);
        if (!arqueiroRepository.existsById(id)) {
            logger.warn("Arqueiro ID {} não encontrado para atualização.", id);
            return Optional.empty();
        }
        arqueiroAtualizado.setId(id);
        return Optional.of(arqueiroRepository.save(arqueiroAtualizado)); // Salva o arqueiro atualizado e retorna um Optional contendo o objeto salvo.
    }

    @Transactional
    public boolean deletarPorId(Long id) {
        logger.info("Solicitação para deletar arqueiro ID: {}", id);
        if (!arqueiroRepository.existsById(id)) {
            logger.warn("Arqueiro ID {} não encontrado para exclusão.", id);
            return false;
        }
        arqueiroRepository.deleteById(id);
        logger.info("Arqueiro ID {} deletado com sucesso.", id);
        return true; // Retorna true se o arqueiro foi deletado, false se não foi encontrado.
    }

    @Transactional
    public Optional<Arqueiro> atualizarParcial(Long id, Arqueiro dadosParciais) {
        logger.info("Iniciando atualização parcial (PATCH) para o ID: {}", id);
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