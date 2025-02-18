package br.edu.ufape.sgi.dados;

import br.edu.ufape.sgi.models.TipoUnidadeAdministrativa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoUnidadeAdministrativaRepository extends JpaRepository<TipoUnidadeAdministrativa, Long> {
    Optional<TipoUnidadeAdministrativa> findByNome(String nome);
}
