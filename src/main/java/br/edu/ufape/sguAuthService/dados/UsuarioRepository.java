package br.edu.ufape.sguAuthService.dados;

import br.edu.ufape.sguAuthService.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByKcId(String kcId);
    List<Usuario> findByAtivoTrue();

    @Query("SELECT u FROM Usuario u JOIN u.perfis p WHERE TYPE(p) = Aluno AND u.ativo = true")
    List<Usuario> findUsuariosAlunos();
    @Query("SELECT u FROM Usuario u JOIN u.perfis p WHERE TYPE(p) = Professor AND u.ativo = true")
    List<Usuario> findUsuariosProfessores();
    @Query("SELECT u FROM Usuario u JOIN u.perfis p WHERE TYPE(p) = Tecnico AND u.ativo = true")
    List<Usuario> findUsuariosTecnicos();
}