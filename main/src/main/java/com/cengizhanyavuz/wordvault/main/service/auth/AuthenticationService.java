package com.cengizhanyavuz.wordvault.main.service.auth;

import com.cengizhanyavuz.wordvault.main.dto.request.LoginRequest;
import com.cengizhanyavuz.wordvault.main.dto.request.RegisterRequest;
import com.cengizhanyavuz.wordvault.main.dto.response.AuthenticationResponse;
import com.cengizhanyavuz.wordvault.main.exception.UsernameAlreadyExistsException;
import com.cengizhanyavuz.wordvault.main.model.user.Role;
import com.cengizhanyavuz.wordvault.main.model.user.Token;
import com.cengizhanyavuz.wordvault.main.model.user.User;
import com.cengizhanyavuz.wordvault.main.repository.TokenRepository;
import com.cengizhanyavuz.wordvault.main.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.cengizhanyavuz.wordvault.main.constants.PointConstants.USER_POINT;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        if (repository.existsByUsername(request.username())) {
            throw new UsernameAlreadyExistsException("Username already exists for : " + request.username());
        }
        var user = User.builder()
                .name(request.name())
                .username(request.username())
                .surname(request.surname())
                .password(passwordEncoder.encode(request.password()))
                .age(request.age())
                .gender(request.gender())
                .role(Role.ROLE_USER)
                .elo(USER_POINT)
                .build();
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                    .firstname(savedUser.getName())
                .build();
    }

    public AuthenticationResponse authenticate(LoginRequest request) {

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        var user = repository.findByUsername(request.username())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .firstname(user.getName())
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
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

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userName;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userName = jwtService.extractUsername(refreshToken);
        if (userName != null) {
            var user = this.repository.findByUsername(userName)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
