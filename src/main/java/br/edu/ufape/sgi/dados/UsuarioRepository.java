package br.edu.ufape.sgi.dados;

import br.edu.ufape.sgi.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByKcId(String kcId);
    List<Usuario> findByAtivoTrue();

    @Query("SELECT u FROM Usuario u JOIN u.perfis p WHERE TYPE(p) = Aluno AND u.ativo = true")
    List<Usuario> findUsuariosAlunos();
}