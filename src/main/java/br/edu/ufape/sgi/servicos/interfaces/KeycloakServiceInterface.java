package br.edu.ufape.sgi.servicos.interfaces;

import br.edu.ufape.sgi.comunicacao.dto.auth.TokenResponse;
import br.edu.ufape.sgi.exceptions.auth.KeycloakAuthenticationException;
import jakarta.annotation.PostConstruct;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.Collections;

public interface KeycloakServiceInterface {
    static UserRepresentation getUserRepresentation(String email, String password) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);

        // Configurar o novo usuário
        UserRepresentation user = new UserRepresentation();
        user.setUsername(email);
        user.setFirstName(email);
        user.setLastName(email);
        user.setEmail(email);
        user.setEnabled(true);
        user.setEmailVerified(true);
        user.setCredentials(Collections.singletonList(credential));
        return user;
    }

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
}
