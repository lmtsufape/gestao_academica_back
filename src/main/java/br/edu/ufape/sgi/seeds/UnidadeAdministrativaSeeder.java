package br.edu.ufape.sgi.seeds;

import br.edu.ufape.sgi.dados.TipoUnidadeAdministrativaRepository;
import br.edu.ufape.sgi.dados.UnidadeAdministrativaRepository;
import br.edu.ufape.sgi.models.TipoUnidadeAdministrativa;
import br.edu.ufape.sgi.models.UnidadeAdministrativa;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UnidadeAdministrativaSeeder implements CommandLineRunner {
    private final UnidadeAdministrativaRepository unidadeAdministrativaRepository;
    private final TipoUnidadeAdministrativaRepository tipoUnidadeAdministrativaRepository;

    public UnidadeAdministrativaSeeder(UnidadeAdministrativaRepository unidadeAdministrativaRepository, TipoUnidadeAdministrativaRepository tipoUnidadeAdministrativaRepository) {
        this.unidadeAdministrativaRepository = unidadeAdministrativaRepository;
        this.tipoUnidadeAdministrativaRepository = tipoUnidadeAdministrativaRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (unidadeAdministrativaRepository.count() == 0) {
            Optional<TipoUnidadeAdministrativa> proReitoria = tipoUnidadeAdministrativaRepository.findByNome("Pró-Reitoria");
            Optional<TipoUnidadeAdministrativa> reitoria = tipoUnidadeAdministrativaRepository.findByNome("Reitoria");

            if (proReitoria.isPresent() && reitoria.isPresent()) {
                unidadeAdministrativaRepository.save(new UnidadeAdministrativa(null, "Pró-Reitoria de Administração", "PROAD", proReitoria.get()));
                unidadeAdministrativaRepository.save(new UnidadeAdministrativa(null, "Pró-Reitoria de Ensino e Graduação", "PREG", proReitoria.get()));
                unidadeAdministrativaRepository.save(new UnidadeAdministrativa(null, "Pró-Reitoria de Extensão e Cultura", "PREC", proReitoria.get()));
                unidadeAdministrativaRepository.save(new UnidadeAdministrativa(null, "Pró-Reitoria de Planejamento", "PROPLAN", proReitoria.get()));
                unidadeAdministrativaRepository.save(new UnidadeAdministrativa(null, "Pró-Reitoria de Pesquisa, Pós-Graduação e Inovação", "PRPPGI", proReitoria.get()));
                unidadeAdministrativaRepository.save(new UnidadeAdministrativa(null, "Pró-Reitoria de Gestão de Pessoas", "PROGEPE", proReitoria.get()));
                unidadeAdministrativaRepository.save(new UnidadeAdministrativa(null, "Reitoria", "REIT", reitoria.get()));

                System.out.println("Unidades Administrativas inseridas com sucesso.");
            } else {
                System.out.println("Os tipos necessários não foram encontrados no banco.");
            }
        } else {
            System.out.println("Seed de Unidades Administrativas já foi executado anteriormente.");
        }
    }
}
