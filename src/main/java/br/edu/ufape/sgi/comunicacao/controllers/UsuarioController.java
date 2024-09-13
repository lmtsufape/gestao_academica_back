package br.edu.ufape.sgi.comunicacao.controllers;




import br.edu.ufape.sgi.comunicacao.dto.usuario.UsuarioPatchRequest;
import br.edu.ufape.sgi.comunicacao.dto.usuario.UsuarioRequest;
import br.edu.ufape.sgi.comunicacao.dto.usuario.UsuarioResponse;
import br.edu.ufape.sgi.exceptions.usuario.UsuarioNotFoundException;
import br.edu.ufape.sgi.fachada.Fachada;

import br.edu.ufape.sgi.models.Usuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {
    private final Fachada fachada;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<UsuarioResponse> salvar(@Valid @RequestBody UsuarioRequest usuario) {
        Usuario response = fachada.salvar(usuario.convertToEntity(usuario, modelMapper));
        return new ResponseEntity<>(new UsuarioResponse(response, modelMapper), HttpStatus.CREATED);
    }

    @GetMapping("/{id}") ResponseEntity<UsuarioResponse> buscarVisitante(@PathVariable Long id) throws UsuarioNotFoundException {
        Usuario response = fachada.buscarUsuario(id);
        return new ResponseEntity<>(new UsuarioResponse(response, modelMapper), HttpStatus.OK);
    }

    @GetMapping
    List<UsuarioResponse> listarUsuarios() {
        return fachada.listarUsuarios().stream().map(usuario -> new UsuarioResponse(usuario, modelMapper)).toList();
    }

    @PatchMapping("/{id}")
    ResponseEntity<UsuarioResponse> atualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioPatchRequest entity) throws UsuarioNotFoundException{
        Usuario usuario = fachada.buscarUsuario(id);
        modelMapper.map(entity, usuario);
        return new ResponseEntity<>(new UsuarioResponse(fachada.salvar(usuario), modelMapper), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletarUsuario(@PathVariable Long id) throws UsuarioNotFoundException {
        fachada.deletarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
