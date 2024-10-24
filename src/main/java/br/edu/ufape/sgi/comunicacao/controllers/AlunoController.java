package br.edu.ufape.sgi.comunicacao.controllers;


import br.edu.ufape.sgi.comunicacao.dto.aluno.AlunoResponse;
import br.edu.ufape.sgi.exceptions.notFoundExceptions.AlunoNotFoundException;
import br.edu.ufape.sgi.fachada.Fachada;
import br.edu.ufape.sgi.models.Aluno;
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

    @GetMapping("/{id}") ResponseEntity<AlunoResponse> buscarAluno(@PathVariable Long id) throws AlunoNotFoundException {
        Aluno response = fachada.buscarAluno(id);
        return new ResponseEntity<>(new AlunoResponse(response, modelMapper), HttpStatus.OK);
    }

    @GetMapping List<AlunoResponse> listarAlunos() {
        return fachada.listarAlunos().stream().map(aluno -> new AlunoResponse(aluno, modelMapper)).toList();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletarAluno(@PathVariable Long id) throws AlunoNotFoundException {
        fachada.deletarAluno(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
