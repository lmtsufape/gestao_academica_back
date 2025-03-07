package br.edu.ufape.sgi.comunicacao.controllers;

import br.edu.ufape.sgi.comunicacao.dto.unidadeAdministrativa.UnidadeAdministrativaPatchRequest;
import br.edu.ufape.sgi.comunicacao.dto.unidadeAdministrativa.UnidadeAdministrativaRequest;
import br.edu.ufape.sgi.comunicacao.dto.unidadeAdministrativa.UnidadeAdministrativaResponse;
import br.edu.ufape.sgi.exceptions.unidadeAdministrativa.UnidadeAdministrativaNotFoundException;
import br.edu.ufape.sgi.exceptions.TipoUnidadeAdministrativa.TipoUnidadeAdministrativaNotFoundException;
import br.edu.ufape.sgi.models.TipoUnidadeAdministrativa;
import br.edu.ufape.sgi.fachada.Fachada;
import br.edu.ufape.sgi.models.UnidadeAdministrativa;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/unidade-administrativa") @RequiredArgsConstructor

public class UnidadeAdministrativaController {
    private final Fachada fachada;
    private final ModelMapper modelMapper;

    @PostMapping(value = "/registrar", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UnidadeAdministrativaResponse> salvar(@Valid @RequestBody UnidadeAdministrativaRequest unidadeAdministrativa) {
        UnidadeAdministrativa response = fachada.salvar(unidadeAdministrativa.convertToEntity(unidadeAdministrativa, modelMapper));
        return new ResponseEntity<>(new UnidadeAdministrativaResponse(response, modelMapper), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UnidadeAdministrativaResponse> buscarUnidadeAdministrativa(@PathVariable Long id) throws UnidadeAdministrativaNotFoundException {
        UnidadeAdministrativa response = fachada.buscarUnidadeAdministrativa(id);
        return new ResponseEntity<>(new UnidadeAdministrativaResponse(response, modelMapper), HttpStatus.OK);
    }

    @GetMapping(value = "/listar", produces = "application/json")
    public List<UnidadeAdministrativaResponse> listarUnidadesAdministrativas() {
        return fachada.listarUnidadesAdministrativas().stream().map(unidadeAdministrativa -> new UnidadeAdministrativaResponse(unidadeAdministrativa, modelMapper)).toList();
    }

    @PatchMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UnidadeAdministrativaResponse> atualizarUnidadeAdministrativa(@PathVariable Long id, @Valid @RequestBody UnidadeAdministrativaPatchRequest entity) throws UnidadeAdministrativaNotFoundException{
        UnidadeAdministrativa unidadeAdministrativa = fachada.buscarUnidadeAdministrativa(id);
        modelMapper.map(entity, unidadeAdministrativa);
        return new ResponseEntity<>(new UnidadeAdministrativaResponse(fachada.salvar(unidadeAdministrativa), modelMapper), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> deletarUnidadeAdministrativa(@PathVariable Long id) throws UnidadeAdministrativaNotFoundException {
        fachada.deletarUnidadeAdministrativa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
