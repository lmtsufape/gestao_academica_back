package br.edu.ufape.sgi.comunicacao.controllers;


import br.edu.ufape.sgi.comunicacao.dto.auth.TokenResponse;
import br.edu.ufape.sgi.fachada.Fachada;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    final private Fachada fachada;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestParam("email") String username, @RequestParam("senha") String password) {
        TokenResponse response = fachada.login(username, password);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@RequestParam("refresh_token") String refreshToken) {
        TokenResponse response = fachada.refresh(refreshToken);
        return ResponseEntity.ok(response);
    }
}

