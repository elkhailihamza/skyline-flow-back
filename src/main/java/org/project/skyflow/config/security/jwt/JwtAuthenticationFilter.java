package org.project.skyflow.config.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends JwtAuthenticationFilterProcessor {
    private final JwtProvider jwtProvider;

    public JwtAuthenticationFilter(JwtProvider jwtProvider, @Lazy AuthenticationManager authenticationManager) {
        this.jwtProvider = jwtProvider;
        setAuthenticationManager(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        attemptAuthentication(request, response, filterChain);
        filterChain.doFilter(request, response);
    }

    @Override
    public void attemptAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        String token = jwtProvider.getJwtFromHeader(request);
        String refreshToken = jwtProvider.getRefreshTokenFromHeader(request);

        if (refreshToken != null) {
            if (jwtProvider.isJwtExpired(token, false) && !jwtProvider.isJwtExpired(refreshToken, true)) {
                // skip for now
            }
        }

        if (token != null && jwtProvider.validateJwtToken(token, false)) {
            Authentication authentication = getAuthenticationManager().authenticate(new JwtAuthenticationToken(null, token));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}
