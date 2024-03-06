package com.sfacl.magnus.config;

import com.sfacl.magnus.entity.User;
import com.sfacl.magnus.entity.UserRecord;
import com.sfacl.magnus.repository.UserRepository;
import com.sfacl.magnus.service.PasswordExpirationService;
import com.sfacl.magnus.service.UserRecordService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserRecordService userRecordService;
    private final UserRepository userRepository;


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);


                Optional<User> userOptional = userRepository.findByEmail(userEmail);
                userOptional.ifPresent(this::recordUserLogin);

            }
        }
        filterChain.doFilter(request, response);

    }
    private void recordUserLogin(User user) {
        // Create a UserRecord object and set the login time and user ID
        UserRecord userRecord = new UserRecord();
        userRecord.setLoginTime(LocalDateTime.now());
        userRecord.setUser(user); // Set the User object

        // Save the UserRecord object
        userRecordService.save(userRecord);

        updateLogoutTime(userRecord);
    }
    private void updateLogoutTime(UserRecord userRecord) {
        // Fetch the user record from the database to update the logout time
        userRecord.setLogoutTime(LocalDateTime.now());

        // Update the UserRecord object with the logout time
        userRecordService.save(userRecord);
    }



}
