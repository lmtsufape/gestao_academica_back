package br.edu.ufape.sguAuthService.comunicacao.controllers;


import br.edu.ufape.sguAuthService.comunicacao.dto.professor.ProfessorResponse;
import br.edu.ufape.sguAuthService.exceptions.notFoundExceptions.ProfessorNotFoundException;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/professor")
public class ProfessorController {
    private final Fachada fachada;
    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    ResponseEntity<ProfessorResponse> buscarProfessor(@PathVariable Long id) throws ProfessorNotFoundException, UsuarioNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt principal = (Jwt) authentication.getPrincipal();
        Usuario response = fachada.buscarProfessor(id, principal.getSubject());
        return new ResponseEntity<>(new ProfessorResponse(response, modelMapper), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping
    List<ProfessorResponse> listarProfessores() {
        return fachada.listarProfessores().stream().map(usuario -> new ProfessorResponse(usuario, modelMapper)).toList();
    }
}
