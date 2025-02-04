package br.edu.ufape.sgi.servicos.interfaces;

import br.edu.ufape.sgi.comunicacao.dto.auth.TokenResponse;
import br.edu.ufape.sgi.exceptions.auth.KeycloakAuthenticationException;
import jakarta.annotation.PostConstruct;


public interface KeycloakServiceInterface {


    @PostConstruct
    void init();

    TokenResponse login(String email, String password) throws KeycloakAuthenticationException;

    TokenResponse refreshToken(String refreshToken);

    void logout(String accessToken, String refreshToken);

    void createUser(String email, String password, String role) throws KeycloakAuthenticationException;

    void addRoleToUser(String userId, String role);

    void deleteUser(String userId);

    String getUserId(String username);

    boolean hasRoleAdmin(String accessToken);

    void resetPassword(String email);
}
