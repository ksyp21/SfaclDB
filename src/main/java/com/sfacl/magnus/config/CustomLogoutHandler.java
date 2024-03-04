package com.sfacl.magnus.config;

import com.sfacl.magnus.entity.Token;
import com.sfacl.magnus.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CustomLogoutHandler implements LogoutHandler {

    private final TokenRepository tokenRepository;

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader== null||!authHeader.startsWith("Bearer ")){
            return;
        }
        String token = authHeader.substring(7);

        //get stored token from database
        Token storedToken=tokenRepository.findByToken(token).orElse(null);

        //invalidate the token i.e make logout true
        if(token!= null){
            storedToken.setLoggedOut(true);
            tokenRepository.save(storedToken);
        }


    }
}
