package br.edu.ufape.sgi.comunicacao.controllers;

import br.edu.ufape.sgi.comunicacao.dto.tecnico.TecnicoResponse;
import br.edu.ufape.sgi.exceptions.notFoundExceptions.TecnicoNotFoundException;
import br.edu.ufape.sgi.exceptions.notFoundExceptions.UsuarioNotFoundException;
import br.edu.ufape.sgi.fachada.Fachada;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
@RequestMapping("/tecnico")
public class TecnicoController {
    private final Fachada fachada;
    private final ModelMapper modelMapper;

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping
    List<TecnicoResponse> listarTecnicos() {
        return fachada.listarTecnicos().stream().map(usuario -> new TecnicoResponse(usuario, modelMapper)).toList();
    }

    @GetMapping("/{id}")
    TecnicoResponse buscarTecnico(@PathVariable Long id) throws TecnicoNotFoundException, UsuarioNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt principal = (Jwt) authentication.getPrincipal();
        return new TecnicoResponse(fachada.buscarTecnico(id, principal.getSubject()), modelMapper);
    }
}
