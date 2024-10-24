package br.edu.ufape.sgi.dados;

import br.edu.ufape.sgi.models.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}