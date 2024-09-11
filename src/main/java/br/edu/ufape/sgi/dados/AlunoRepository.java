package br.edu.ufape.sgi.dados;

import br.edu.ufape.sgi.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
