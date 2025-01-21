package org.project.skyflow.config.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.filter.OncePerRequestFilter;

public abstract class JwtAuthenticationFilterProcessor extends OncePerRequestFilter {
    private AuthenticationManager authenticationManager;

    protected void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    protected AuthenticationManager getAuthenticationManager() {
        return this.authenticationManager;
    }

    protected abstract void attemptAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain);

    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {};

    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) {};
}
