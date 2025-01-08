package org.project.skyflow.config.security.auth;

import org.project.skyflow.config.security.SecurityUser;
import org.project.skyflow.exception.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthFacadeImpl implements AuthFacade {
    @Override
    public SecurityUser getAuthenticatedUser() {
        Authentication authentication;
        authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return (SecurityUser) authentication.getPrincipal();
            }
        }

        throw new AuthenticationCredentialsNotFoundException("User is not authenticated!");
    }

    @Override
    public String getUserName() {
        SecurityUser securityUser = this.getAuthenticatedUser();
        if (securityUser.getUsername() != null) {
            return securityUser.getUsername();
        }
        throw new UsernameNotFoundException("Username not found!");
    }

    @Override
    public long getUserId() {
        SecurityUser securityUser = this.getAuthenticatedUser();
        return securityUser.getId();
    }
}
