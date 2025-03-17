package br.edu.ufape.sguAuthService.servicos.interfaces;

import br.edu.ufape.sguAuthService.comunicacao.dto.auth.TokenResponse;
import br.edu.ufape.sguAuthService.exceptions.auth.KeycloakAuthenticationException;
import jakarta.annotation.PostConstruct;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;


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

    List<UserRepresentation> listUnverifiedUsers();
}
