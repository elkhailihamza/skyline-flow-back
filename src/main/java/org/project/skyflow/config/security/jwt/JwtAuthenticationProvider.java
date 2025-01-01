package org.project.skyflow.config.security.jwt;

import lombok.RequiredArgsConstructor;
import org.project.skyflow.config.security.SecurityUser;
import org.project.skyflow.config.security.SecurityUserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtProvider jwtProvider;
    private final SecurityUserService securityUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getCredentials();
        if (token == null || !jwtProvider.validateJwtToken(token, false)) {
            return null;
        }

        String username = jwtProvider.getUserNameFromJwtToken(token, false);
        SecurityUser securityUser = securityUserService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(securityUser, token, securityUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
