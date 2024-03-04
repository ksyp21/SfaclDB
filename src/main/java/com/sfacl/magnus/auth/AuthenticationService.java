package com.sfacl.magnus.auth;

import com.sfacl.magnus.config.JWTService;
import com.sfacl.magnus.entity.Role;
import com.sfacl.magnus.entity.Token;
import com.sfacl.magnus.entity.User;
import com.sfacl.magnus.repository.TokenRepository;
import com.sfacl.magnus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final TokenRepository tokenRepository;

    private final JWTService jwtService;
    public AuthenticationResponse register(RegisterRequest request) {
        var user= User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) //password change mechanism?
                .role(Role.STAFF)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);

        //save the generated token
        saveUserToken(jwtToken, user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
        }

    public AuthenticationResponse authenticate(ApplicationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        revokeAllTokenByUser(user);

        saveUserToken(jwtToken,user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void revokeAllTokenByUser(User user) {
        List<Token> validTokenListByUser = tokenRepository.findALlTokenByUser(user.getId());
        if(!validTokenListByUser.isEmpty()){
            validTokenListByUser.forEach(t->{
                t.setLoggedOut(true);
            });
        }
        tokenRepository.saveAll(validTokenListByUser);
    }

    private void saveUserToken(String jwtToken, User user) {
        Token token= new Token();
        token.setToken(jwtToken);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }
    }



