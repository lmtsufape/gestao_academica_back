package br.edu.ufape.sguAuthService.dados;

import br.edu.ufape.sguAuthService.models.UnidadeAdministrativa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadeAdministrativaRepository extends JpaRepository<UnidadeAdministrativa, Long> {
}
