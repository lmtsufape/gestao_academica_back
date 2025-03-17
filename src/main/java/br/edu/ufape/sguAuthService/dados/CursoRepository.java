package br.edu.ufape.sguAuthService.dados;

import br.edu.ufape.sguAuthService.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByNomeEqualsIgnoreCase(String nome);
    List<Curso> findByAtivoTrue();
}