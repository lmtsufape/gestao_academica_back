package br.edu.ufape.sguAuthService.comunicacao.controllers;

import br.edu.ufape.sguAuthService.comunicacao.dto.unidadeAdministrativa.UnidadeAdministrativaPatchRequest;
import br.edu.ufape.sguAuthService.comunicacao.dto.unidadeAdministrativa.UnidadeAdministrativaRequest;
import br.edu.ufape.sguAuthService.comunicacao.dto.unidadeAdministrativa.UnidadeAdministrativaResponse;
import br.edu.ufape.sguAuthService.exceptions.unidadeAdministrativa.UnidadeAdministrativaNotFoundException;
import br.edu.ufape.sguAuthService.fachada.Fachada;
import br.edu.ufape.sguAuthService.models.UnidadeAdministrativa;
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

    @PostMapping
    public ResponseEntity<UnidadeAdministrativaResponse> salvar(@Valid @RequestBody UnidadeAdministrativaRequest unidadeAdministrativa) {
        UnidadeAdministrativa response = fachada.salvar(unidadeAdministrativa.convertToEntity(unidadeAdministrativa, modelMapper));
        return new ResponseEntity<>(new UnidadeAdministrativaResponse(response, modelMapper), HttpStatus.CREATED);
    }

    @GetMapping("/{id}") ResponseEntity<UnidadeAdministrativaResponse> buscarUnidadeAdministrativa(@PathVariable Long id) throws UnidadeAdministrativaNotFoundException {
        UnidadeAdministrativa response = fachada.buscarUnidadeAdministrativa(id);
        return new ResponseEntity<>(new UnidadeAdministrativaResponse(response, modelMapper), HttpStatus.OK);
    }

    @GetMapping
    List<UnidadeAdministrativaResponse> listarUnidadesAdministrativas() {
        return fachada.listarUnidadesAdministrativas().stream().map(unidadeAdministrativa -> new UnidadeAdministrativaResponse(unidadeAdministrativa, modelMapper)).toList();
    }

    @PatchMapping("/{id}")
    ResponseEntity<UnidadeAdministrativaResponse> atualizarUnidadeAdministrativa(@PathVariable Long id, @Valid @RequestBody UnidadeAdministrativaPatchRequest entity) throws UnidadeAdministrativaNotFoundException{
        UnidadeAdministrativa unidadeAdministrativa = fachada.buscarUnidadeAdministrativa(id);
        modelMapper.map(entity, unidadeAdministrativa);
        return new ResponseEntity<>(new UnidadeAdministrativaResponse(fachada.salvar(unidadeAdministrativa), modelMapper), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletarUnidadeAdministrativa(@PathVariable Long id) throws UnidadeAdministrativaNotFoundException {
        fachada.deletarUnidadeAdministrativa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
