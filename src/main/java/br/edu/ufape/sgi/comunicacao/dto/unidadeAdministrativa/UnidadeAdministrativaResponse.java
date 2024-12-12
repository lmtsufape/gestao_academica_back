package br.edu.ufape.sgi.comunicacao.dto.unidadeAdministrativa;

import br.edu.ufape.sgi.models.Aluno;
import br.edu.ufape.sgi.models.UnidadeAdministrativa;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter

public class UnidadeAdministrativaResponse {
    public UnidadeAdministrativaResponse(UnidadeAdministrativa unidadeAdministrativa, ModelMapper modelMapper){
        if (unidadeAdministrativa == null) throw new IllegalArgumentException("Unidade Administrativa não pode ser nula");
        else modelMapper.map(unidadeAdministrativa, this);
    }
}
