package br.edu.ufape.sgi.comunicacao.controllers;


import br.edu.ufape.sgi.comunicacao.dto.curso.CursoRequest;
import br.edu.ufape.sgi.comunicacao.dto.curso.CursoResponse;
import br.edu.ufape.sgi.exceptions.CursoDuplicadoException;
import br.edu.ufape.sgi.exceptions.notFoundExceptions.CursoNotFoundException;
import br.edu.ufape.sgi.fachada.Fachada;
import br.edu.ufape.sgi.models.Curso;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController@RequiredArgsConstructor
@RequestMapping("/curso")
public class CursoController {
    private final Fachada fachada;
    private final ModelMapper modelMapper;


    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/registrar")
    public ResponseEntity<CursoResponse> salvar(@Valid @RequestBody CursoRequest curso) throws CursoDuplicadoException {
        Curso response = fachada.salvarCurso(curso.convertToEntity(curso, modelMapper));
        return new ResponseEntity<>(new CursoResponse(response, modelMapper), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PatchMapping("/{id}/editar")
    public ResponseEntity<CursoResponse> editar(@PathVariable Long id, @Valid @RequestBody CursoRequest curso) throws CursoNotFoundException, CursoDuplicadoException {
        Curso response = fachada.editarCurso(id, curso.convertToEntity(curso, modelMapper));
        return new ResponseEntity<>(new CursoResponse(response, modelMapper), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponse> buscar(@PathVariable Long id) throws CursoNotFoundException {
        Curso response = fachada.buscarCurso(id);
        return new ResponseEntity<>(new CursoResponse(response, modelMapper), HttpStatus.OK);
    }

    @GetMapping
    public List<CursoResponse> listar() {
        return fachada.listarCursos().stream().map(curso -> new CursoResponse(curso, modelMapper)).toList();
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/{id}/deletar")
    public ResponseEntity<Void> deletar(@PathVariable Long id) throws CursoNotFoundException {
        fachada.deletarCurso(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
