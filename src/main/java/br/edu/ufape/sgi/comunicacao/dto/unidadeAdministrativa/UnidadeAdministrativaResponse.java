package br.edu.ufape.sgi.comunicacao.dto.unidadeAdministrativa;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.edu.ufape.sgi.models.UnidadeAdministrativa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeAdministrativaResponse {
    private Long id;
    private String nome;
    private String codigo;
    private String tipoUnidadeAdministrativa; //Trocar para a entidade TipoUnidadeAdministrativa
    private List<UnidadeAdministrativaResponse> unidadesFilhas = new ArrayList<>();




    public UnidadeAdministrativaResponse(UnidadeAdministrativa unidadeAdministrativa, ModelMapper modelMapper) {
        if (unidadeAdministrativa == null) throw new IllegalArgumentException("Unidade Administrativa não pode ser nula");
        modelMapper.map(unidadeAdministrativa, this);
        this.tipoUnidadeAdministrativa = unidadeAdministrativa.getTipoUnidadeAdministrativa().getNome(); // Isso nã será necessário, pois o tipo de unidade administrativa será uma entidade
        //converte as unidades filhas
        if (!unidadeAdministrativa.getUnidadesFilhas().isEmpty()) {
            for (UnidadeAdministrativa unidadeFilha : unidadeAdministrativa.getUnidadesFilhas()) {
                this.unidadesFilhas.add(new UnidadeAdministrativaResponse(unidadeFilha, modelMapper));
            }
        } else {
            this.unidadesFilhas = null;
        }
        //caso essa condição não de certo bubstitua por 
        /*if (unidadeAdministrativa.getUnidadesFilhas() != null) {
            this.unidadesfilhas = unidadeAdministrativa.getUnidadesFilhas()
                .stream()
                .map(filha -> new UnidadeAdministrativaResponse(filha, modelMapper))
                .collect(Collectors.toList());
        } */
    }
}