package br.edu.ufape.sgi.seeds;

import br.edu.ufape.sgi.dados.TipoUnidadeAdministrativaRepository;
import br.edu.ufape.sgi.models.TipoUnidadeAdministrativa;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TipoUnidadeAdministrativaSeeder implements CommandLineRunner {
    private final TipoUnidadeAdministrativaRepository tipoRepository;

    public TipoUnidadeAdministrativaSeeder(TipoUnidadeAdministrativaRepository tipoRepository) {
        this.tipoRepository = tipoRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (tipoRepository.count() == 0) {
            TipoUnidadeAdministrativa reitoria = tipoRepository.save(new TipoUnidadeAdministrativa(null, "Reitoria"));
            TipoUnidadeAdministrativa proReitoria = tipoRepository.save(new TipoUnidadeAdministrativa(null, "Pró-Reitoria"));
            TipoUnidadeAdministrativa departamento = tipoRepository.save(new TipoUnidadeAdministrativa(null, "Departamento"));
            TipoUnidadeAdministrativa setor = tipoRepository.save(new TipoUnidadeAdministrativa(null, "Setor"));

            System.out.println("Tipos de Unidade Administrativa inseridos com sucesso.");
        } else {
            System.out.println("Tipos de Unidade Administrativa já existem.");
        }
    }
}
