package com.api.auth.api.authenticator.services;

import com.api.auth.api.authenticator.entities.Role;
import com.api.auth.api.authenticator.entities.Token;
import com.api.auth.api.authenticator.entities.User;
import com.api.auth.api.authenticator.enums.RoleType;
import com.api.auth.api.authenticator.enums.TokenType;
import com.api.auth.api.authenticator.records.AuthenticationRequest;
import com.api.auth.api.authenticator.records.AuthenticationResponse;
import com.api.auth.api.authenticator.records.RegisterRequest;
import com.api.auth.api.authenticator.repositories.RoleRepository;
import com.api.auth.api.authenticator.repositories.TokenRepository;
import com.api.auth.api.authenticator.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final RoleRepository roleRepository;

    public AuthenticationResponse register(RegisterRequest request) {

        var role = roleRepository.findByRole(RoleType.USER.name());

        var user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .enabled(Boolean.TRUE)
                .password(passwordEncoder.encode(request.password()))
                .role(role.get())
                .build();

        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);

        saveUserToken(savedUser, jwtToken);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var user = repository.findByEmail(request.email()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();

        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
