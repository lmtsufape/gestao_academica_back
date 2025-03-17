package br.edu.ufape.sguAuthService.comunicacao.controllers;


import br.edu.ufape.sguAuthService.comunicacao.dto.auth.TokenResponse;
import br.edu.ufape.sguAuthService.fachada.Fachada;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String token, @RequestParam("refresh_token") String refreshToken) {
        String accessToken = token.replace("Bearer ", "");
        fachada.logout(accessToken, refreshToken);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestParam("email") String email) {
        fachada.resetPassword(email);
        return ResponseEntity.noContent().build();
    }
}

