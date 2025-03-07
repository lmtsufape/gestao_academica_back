/*package br.edu.ufape.sgi.seeds;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import br.edu.ufape.sgi.models.UnidadeAdministrativa;
import br.edu.ufape.sgi.models.Enums.TipoUnidadeAdministrativa;
import br.edu.ufape.sgi.dados.UnidadeAdministrativaRepository;

@Component
public class UnidadeAdministrativaSeeder implements CommandLineRunner {
    private final UnidadeAdministrativaRepository unidadeAdministrativaRepository;

    public UnidadeAdministrativaSeeder(UnidadeAdministrativaRepository unidadeAdministrativaRepository) {
        this.unidadeAdministrativaRepository = unidadeAdministrativaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (unidadeAdministrativaRepository.count() == 0) {
            unidadeAdministrativaRepository.save(new UnidadeAdministrativa(null, "Pró-Reitoria de Administração", "PROAD", TipoUnidadeAdministrativa.PROREITORIA));
            unidadeAdministrativaRepository.save(new UnidadeAdministrativa(null, "Pró-Reitoria de Ensino e Graduação", "PREG", TipoUnidadeAdministrativa.PROREITORIA));
            unidadeAdministrativaRepository.save(new UnidadeAdministrativa(null, "Pró-Reitoria de Extensão e CUltura", "PREC", TipoUnidadeAdministrativa.PROREITORIA));
            unidadeAdministrativaRepository.save(new UnidadeAdministrativa(null, "Pró-Reitoria de Planejamento", "PROPLAN", TipoUnidadeAdministrativa.PROREITORIA));
            unidadeAdministrativaRepository.save(new UnidadeAdministrativa(null, "Pro-Reitoria de Pesquisa, Pós-Graduação e Inovação", "PRPPGI", TipoUnidadeAdministrativa.PROREITORIA));
            unidadeAdministrativaRepository.save(new UnidadeAdministrativa(null, "Pró-Reitoria de Gestão de Pessoas", "PROGEPE", TipoUnidadeAdministrativa.PROREITORIA));
            unidadeAdministrativaRepository.save(new UnidadeAdministrativa(null, "Reitoria", "REIT", TipoUnidadeAdministrativa.REITORIA));

            System.out.println("Unidades Administrativas inseridas com sucesso.");
        } else {
            System.out.println("Seed já foi executado anteriormente.");
        }
    }
}*/
