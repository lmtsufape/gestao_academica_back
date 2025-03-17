package br.edu.ufape.sguAuthService.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class KeycloakJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter defaultGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    private final ObjectMapper objectMapper = new ObjectMapper();



    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
        Collection<GrantedAuthority> authorities = Stream
                .concat(defaultGrantedAuthoritiesConverter.convert(jwt).stream()
                        , extractAuthorities(jwt).stream())
                .collect(Collectors.toSet());
        authorities.forEach(System.out::println);
        return new JwtAuthenticationToken(jwt, extractAuthorities(jwt),jwt.getClaimAsString("preferred_username"));

    }


    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        Set<String> rolesWithPrefix = new HashSet<>(getRealmRoles(jwt));
        return AuthorityUtils.createAuthorityList(rolesWithPrefix.toArray(new String[0]));
    }

    @SuppressWarnings("unused")
    private Set<String> getRealmRoles(Jwt jwt) {
        Set<String> rolesWithPrefix = new HashSet<>();
        JsonNode json = objectMapper.convertValue(jwt.getClaim("realm_access"), JsonNode.class);
        json.elements().forEachRemaining(
                e -> e.elements().forEachRemaining(r -> rolesWithPrefix.add(createRole(r.asText()))));
        return rolesWithPrefix;
    }


    @SuppressWarnings("unused")
    private Set<String> getResourceRoles(Jwt jwt) {
        Set<String> rolesWithPrefix = new HashSet<>();
        Map<String, JsonNode> map = objectMapper.convertValue(jwt.getClaim("resource_access"), new TypeReference<>() {
        });
        for (Map.Entry<String, JsonNode> jsonNode : map.entrySet()) {
            jsonNode
                    .getValue()
                    .elements()
                    .forEachRemaining(e -> e
                            .elements()
                            .forEachRemaining(r -> rolesWithPrefix.add(createRole(jsonNode.getKey(), r.asText()))));
        }
        return rolesWithPrefix;
    }

    private String createRole(String... values) {
        StringBuilder role = new StringBuilder("ROLE_");  // Adiciona o prefixo ROLE_
        for (String value : values) {
            role.append(value.toUpperCase());
        }
        return role.toString();
    }
}