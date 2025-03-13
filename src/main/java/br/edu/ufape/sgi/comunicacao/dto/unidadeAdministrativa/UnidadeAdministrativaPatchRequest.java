package br.edu.ufape.sgi.comunicacao.dto.unidadeAdministrativa;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeAdministrativaPatchRequest {
    private String nome;
    private String codigo;
   

    public UnidadeAdministrativaPatchRequest convertToEntity(UnidadeAdministrativaPatchRequest unidadeAdministrativaPatchRequest, ModelMapper modelMapper) {
        return modelMapper.map(unidadeAdministrativaPatchRequest, UnidadeAdministrativaPatchRequest.class);
    }
}

