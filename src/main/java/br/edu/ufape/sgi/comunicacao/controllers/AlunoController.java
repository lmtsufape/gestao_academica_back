package br.edu.ufape.sgi.comunicacao.controllers;

import br.edu.ufape.sgi.comunicacao.dto.aluno.AlunoPatchRequest;
import br.edu.ufape.sgi.comunicacao.dto.aluno.AlunoRequest;
import br.edu.ufape.sgi.comunicacao.dto.aluno.AlunoResponse;
import br.edu.ufape.sgi.exceptions.aluno.AlunoNotFoundException;
import br.edu.ufape.sgi.fachada.Fachada;
import br.edu.ufape.sgi.models.Aluno;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/aluno")  @RequiredArgsConstructor

public class AlunoController {
    private final Fachada fachada;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<AlunoResponse> salvar(@Valid @RequestBody AlunoRequest aluno) {
       Aluno response = fachada.salvar(aluno.convertToEntity(aluno, modelMapper));
       return new ResponseEntity<>(new AlunoResponse(response, modelMapper), HttpStatus.CREATED);
    }

    @GetMapping("/{id}") ResponseEntity<AlunoResponse> buscarAluno(@PathVariable Long id) throws AlunoNotFoundException {
        Aluno response = fachada.buscarAluno(id);
        return new ResponseEntity<>(new AlunoResponse(response, modelMapper), HttpStatus.OK);
    }

    @GetMapping List<AlunoResponse> listarAlunos() {
        return fachada.listarAlunos().stream().map(aluno -> new AlunoResponse(aluno, modelMapper)).toList();
    }

    @PatchMapping("/{id}")
    ResponseEntity<AlunoResponse> atualizarAluno(@PathVariable Long id, @Valid @RequestBody AlunoPatchRequest entity) throws AlunoNotFoundException{
        Aluno aluno = fachada.buscarAluno(id);
        modelMapper.map(entity, aluno);
        return new ResponseEntity<>(new AlunoResponse(fachada.salvar(aluno), modelMapper), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletarAluno(@PathVariable Long id) throws AlunoNotFoundException {
        fachada.deletarAluno(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
