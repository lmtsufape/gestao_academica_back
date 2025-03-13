package br.edu.ufape.sgi.comunicacao.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.sgi.comunicacao.dto.TipoUnidadeAdministrativa.TipoUnidadeAdministrativaRequest;
import br.edu.ufape.sgi.comunicacao.dto.TipoUnidadeAdministrativa.TipoUnidadeAdministrativaResponse;
import br.edu.ufape.sgi.exceptions.TipoUnidadeAdministrativaDuplicadoException;
import br.edu.ufape.sgi.exceptions.notFoundExceptions.TipoUnidadeAdministrativaNotFoundException;
import br.edu.ufape.sgi.fachada.Fachada;
import br.edu.ufape.sgi.models.TipoUnidadeAdministrativa;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tipo-unidade-administrativa")
public class TipoUnidadeAdministrativaController {
    private final Fachada fachada;
    private final ModelMapper modelMapper;

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/registrar")
    public ResponseEntity<TipoUnidadeAdministrativaResponse> salvar(@Valid @RequestBody TipoUnidadeAdministrativaRequest tipoUnidadeAdministrativa) throws TipoUnidadeAdministrativaDuplicadoException {
        TipoUnidadeAdministrativa response = fachada.salvarTipo(tipoUnidadeAdministrativa.convertToEntity(tipoUnidadeAdministrativa, modelMapper));
        return new ResponseEntity<>(new TipoUnidadeAdministrativaResponse(response, modelMapper), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PatchMapping("/{id}/editar")
    public ResponseEntity<TipoUnidadeAdministrativaResponse> editar(@PathVariable Long id, @Valid @RequestBody TipoUnidadeAdministrativaRequest tipoUnidadeAdministrativa) throws TipoUnidadeAdministrativaNotFoundException, TipoUnidadeAdministrativaDuplicadoException {
        TipoUnidadeAdministrativa response = fachada.editarTipo(id, tipoUnidadeAdministrativa.convertToEntity(tipoUnidadeAdministrativa, modelMapper));
        return new ResponseEntity<>(new TipoUnidadeAdministrativaResponse(response, modelMapper), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUnidadeAdministrativaResponse> buscar(@PathVariable Long id) throws TipoUnidadeAdministrativaNotFoundException {
        TipoUnidadeAdministrativa response = fachada.buscarTipo(id);
        return new ResponseEntity<>(new TipoUnidadeAdministrativaResponse(response, modelMapper), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TipoUnidadeAdministrativaResponse>> listar() {
        List<TipoUnidadeAdministrativa> tipos = fachada.listarTipos();
        List<TipoUnidadeAdministrativaResponse> response = tipos.stream()
                .map(tipo -> new TipoUnidadeAdministrativaResponse(tipo, modelMapper))
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    /*@GetMapping
    public List<TipoUnidadeAdministrativaResponse> listar() {
        return fachada.listarTipos().stream()
        .map(tipoUnidadeAdministrativa -> new TipoUnidadeAdministrativaResponse(tipoUnidadeAdministrativa, modelMapper))
        .toList();
    }*/

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/{id}/deletar")
    public ResponseEntity<Void> deletar(@PathVariable Long id) throws TipoUnidadeAdministrativaNotFoundException {
        fachada.deletarTipo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}