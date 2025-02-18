package br.edu.ufape.sgi.comunicacao.controllers;

import br.edu.ufape.sgi.comunicacao.dto.tipoUnidadeAdministrativa.TipoUnidadeAdministrativaRequest;
import br.edu.ufape.sgi.comunicacao.dto.tipoUnidadeAdministrativa.TipoUnidadeAdministrativaResponse;
import br.edu.ufape.sgi.exceptions.tipoUnidadeAdministrativa.TipoUnidadeAdministrativaNotFoundException;
import br.edu.ufape.sgi.fachada.Fachada;
import br.edu.ufape.sgi.models.TipoUnidadeAdministrativa;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo-unidade-administrativa")
@RequiredArgsConstructor
public class TipoUnidadeAdministrativaController {
    private final Fachada fachada;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<TipoUnidadeAdministrativaResponse> salvar(@Valid @RequestBody TipoUnidadeAdministrativaRequest tipoUnidadeAdministrativa) {
        TipoUnidadeAdministrativa response = fachada.salvarTipoUnidadeAdministrativa(tipoUnidadeAdministrativa.convertToEntity(tipoUnidadeAdministrativa, modelMapper));
        return new ResponseEntity<>(new TipoUnidadeAdministrativaResponse(response, modelMapper), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUnidadeAdministrativaResponse> buscarTipoUnidadeAdministrativa(@PathVariable Long id) throws TipoUnidadeAdministrativaNotFoundException {
        TipoUnidadeAdministrativa response = fachada.buscarTipoUnidadeAdministrativa(id);
        return new ResponseEntity<>(new TipoUnidadeAdministrativaResponse(response, modelMapper), HttpStatus.OK);
    }

    @GetMapping
    public List<TipoUnidadeAdministrativaResponse> listarTiposUnidadeAdministrativa() {
        return fachada.listarTiposUnidadeAdministrativa().stream()
                .map(tipo -> modelMapper.map(tipo, TipoUnidadeAdministrativaResponse.class))
                .toList();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTipoUnidadeAdministrativa(@PathVariable Long id) throws TipoUnidadeAdministrativaNotFoundException {
        fachada.deletarTipoUnidadeAdministrativa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
