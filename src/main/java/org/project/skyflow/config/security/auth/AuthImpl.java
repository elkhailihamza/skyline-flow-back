package org.project.skyflow.config.security.auth;

import org.project.skyflow.config.security.SecurityUser;
import org.project.skyflow.exception.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthImpl implements Auth{
    @Override
    public SecurityUser getSecurityUser() {
        Authentication authentication;
        authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (SecurityUser) authentication.getPrincipal();
        }

        throw new AuthenticationCredentialsNotFoundException("User is not authenticated!");
    }

    @Override
    public String getUsername() {
        SecurityUser securityUser = this.getSecurityUser();
        if (securityUser.getUsername() != null) {
            return securityUser.getUsername();
        }
        throw new UsernameNotFoundException("Username not found!");
    }

    @Override
    public long getUserId() {
        SecurityUser securityUser = this.getSecurityUser();
        return securityUser.getId();
    }
}
