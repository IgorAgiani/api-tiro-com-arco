package br.com.tirocomarco.api.repository;

import br.com.tirocomarco.api.model.Arqueiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indica ao Spring que esta é uma interface de repositório.
public interface ArqueiroRepository extends JpaRepository<Arqueiro, Long> {
    // JpaRepository fornece métodos CRUD padrão para a entidade Arqueiro.
}
