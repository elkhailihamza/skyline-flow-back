package org.project.skyflow.config.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.skyflow.dto.AuthTokenDTO;
import org.project.skyflow.service.AuthService;
import org.project.skyflow.service.JwtTokenService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class JwtAuthenticationFilter extends JwtAuthenticationFilterProcessor {
    private final JwtProvider jwtProvider;
    private final AuthService authService;
    private final JwtTokenService jwtTokenService;

    public JwtAuthenticationFilter(JwtProvider jwtProvider, @Lazy AuthenticationManager authenticationManager, @Lazy AuthService authService, JwtTokenService jwtTokenService) {
        this.jwtProvider = jwtProvider;
        this.authService = authService;
        this.jwtTokenService = jwtTokenService;
        setAuthenticationManager(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        attemptAuthentication(request, response, filterChain);
        filterChain.doFilter(request, response);
    }

    @Override
    public void attemptAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        Cookie[] userCookies = request.getCookies();

        if (userCookies != null) {
            String token = jwtProvider.getJwtFromHeader(request);
            String refreshToken = jwtProvider.getRefreshTokenFromHeader(request);

            if (refreshToken != null) {
                if (jwtProvider.isJwtExpired(token, false) && !jwtProvider.isJwtExpired(refreshToken, true)) {
                    AuthTokenDTO authTokenDTO = authService.refreshTokens(AuthTokenDTO.builder().jwtRefreshToken(refreshToken).build());

                    authTokenDTO = authService.refreshTokens(authTokenDTO);
                    jwtProvider.setCookies(authTokenDTO, response);

                    token = authTokenDTO.getJwtToken();
                }
            }

            if (token != null && jwtProvider.validateJwtToken(token, false)) {
                Authentication authentication = getAuthenticationManager().authenticate(new JwtAuthenticationToken(null, token));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
    }
}
