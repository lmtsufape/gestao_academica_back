package br.edu.ufape.sgi.dados;

import br.edu.ufape.sgi.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByNomeEqualsIgnoreCase(String nome);
    List<Curso> findByAtivoTrue();
}