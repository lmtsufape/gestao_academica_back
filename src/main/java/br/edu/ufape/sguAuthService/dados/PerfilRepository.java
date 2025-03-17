package br.edu.ufape.sguAuthService.dados;

import br.edu.ufape.sguAuthService.models.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}