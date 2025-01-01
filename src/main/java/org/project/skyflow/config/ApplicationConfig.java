package org.project.skyflow.config;

import lombok.RequiredArgsConstructor;
import org.project.skyflow.config.security.SecurityUserService;
import org.project.skyflow.config.security.jwt.JwtAuthenticationProvider;
import org.project.skyflow.config.security.jwt.JwtProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final JwtProvider jwtProvider;
    private final SecurityUserService securityUserService;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new JwtAuthenticationProvider(jwtProvider, securityUserService);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
