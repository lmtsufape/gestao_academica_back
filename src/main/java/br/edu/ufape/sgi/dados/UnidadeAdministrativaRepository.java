package br.edu.ufape.sgi.dados;

import br.edu.ufape.sgi.models.UnidadeAdministrativa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  UnidadeAdministrativaRepository extends JpaRepository<UnidadeAdministrativa, Long> {
    List<UnidadeAdministrativa> findByUnidadePaiId(Long id);
    List<UnidadeAdministrativa> findByUnidadePaiIsNull();

    boolean existsByCodigo(String codigo);
}
