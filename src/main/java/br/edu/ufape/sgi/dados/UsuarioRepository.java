package br.edu.ufape.sgi.dados;

import br.edu.ufape.sgi.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}