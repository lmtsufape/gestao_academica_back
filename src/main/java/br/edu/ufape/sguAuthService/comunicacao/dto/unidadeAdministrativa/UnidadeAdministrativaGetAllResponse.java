package br.edu.ufape.sguAuthService.comunicacao.dto.unidadeAdministrativa;

import br.edu.ufape.sguAuthService.comunicacao.dto.tipoUnidadeAdministrativa.TipoUnidadeAdministrativaResponse;
import org.modelmapper.ModelMapper;

import br.edu.ufape.sguAuthService.models.UnidadeAdministrativa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor

public class UnidadeAdministrativaGetAllResponse {
    private Long id;
    private String nome;
    private String codigo;
    private TipoUnidadeAdministrativaResponse tipoUnidadeAdministrativa;
    private Long unidadePaiId;

    public UnidadeAdministrativaGetAllResponse(UnidadeAdministrativa unidadeAdministrativa, ModelMapper modelMapper) {
        this.id = unidadeAdministrativa.getId();
        this.nome = unidadeAdministrativa.getNome();
        this.codigo = unidadeAdministrativa.getCodigo();
        this.tipoUnidadeAdministrativa = new TipoUnidadeAdministrativaResponse(unidadeAdministrativa.getTipoUnidadeAdministrativa(), modelMapper);
        this.unidadePaiId = (unidadeAdministrativa.getUnidadePai() != null) ? unidadeAdministrativa.getUnidadePai().getId() : null;
    }
}
