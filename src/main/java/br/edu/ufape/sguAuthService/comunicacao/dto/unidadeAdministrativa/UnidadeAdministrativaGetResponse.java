package br.edu.ufape.sgi.comunicacao.dto.unidadeAdministrativa;

import br.edu.ufape.sgi.comunicacao.dto.TipoUnidadeAdministrativa.TipoUnidadeAdministrativaResponse;
import br.edu.ufape.sgi.models.UnidadeAdministrativa;
import org.modelmapper.ModelMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UnidadeAdministrativaGetResponse {
    private Long id;
    private String nome;
    private String codigo;
    private TipoUnidadeAdministrativaResponse tipoUnidadeAdministrativa;

    public UnidadeAdministrativaGetResponse(UnidadeAdministrativa unidadeAdministrativa, ModelMapper modelMapper) {
        this.id = unidadeAdministrativa.getId();
        this.nome = unidadeAdministrativa.getNome();
        this.codigo = unidadeAdministrativa.getCodigo();
        this.tipoUnidadeAdministrativa = new TipoUnidadeAdministrativaResponse(unidadeAdministrativa.getTipoUnidadeAdministrativa(), modelMapper);
    }
}

