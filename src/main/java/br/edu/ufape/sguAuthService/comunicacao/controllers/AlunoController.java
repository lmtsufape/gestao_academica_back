package br.edu.ufape.sguAuthService.comunicacao.controllers;


import br.edu.ufape.sguAuthService.comunicacao.dto.aluno.AlunoResponse;
import br.edu.ufape.sguAuthService.exceptions.notFoundExceptions.AlunoNotFoundException;
import br.edu.ufape.sguAuthService.exceptions.notFoundExceptions.UsuarioNotFoundException;
import br.edu.ufape.sguAuthService.fachada.Fachada;
import br.edu.ufape.sguAuthService.models.Usuario;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/aluno")  @RequiredArgsConstructor

public class AlunoController {
    private final Fachada fachada;
    private final ModelMapper modelMapper;

    @GetMapping("/{id}") ResponseEntity<AlunoResponse> buscarAluno(@PathVariable Long id) throws AlunoNotFoundException, UsuarioNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt principal = (Jwt) authentication.getPrincipal();
        Usuario response = fachada.buscarAluno(id, principal.getSubject());
        return new ResponseEntity<>(new AlunoResponse(response, modelMapper), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping List<AlunoResponse> listarAlunos() {
        return fachada.listarAlunos().stream().map(usuario -> new AlunoResponse(usuario, modelMapper)).toList();
    }




}
