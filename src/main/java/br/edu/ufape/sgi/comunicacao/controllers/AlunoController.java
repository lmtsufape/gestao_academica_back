package br.edu.ufape.sgi.comunicacao.controllers;

import br.edu.ufape.sgi.fachada.Fachada;
import br.edu.ufape.sgi.models.Aluno;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller @RequestMapping("/aluno")  @RequiredArgsConstructor

public class AlunoController {
    private final Fachada fachada;

    @PostMapping
    public ResponseEntity salvar(@RequestBody Aluno aluno) {
        System.out.println(aluno);
        return new ResponseEntity(fachada.salvar(aluno), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public String teste(){
        return "Ai ai";
    }
}
