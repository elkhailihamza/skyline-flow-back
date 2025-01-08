package org.project.skyflow.config.security.auth;

import org.project.skyflow.config.security.SecurityUser;

public interface AuthFacade {
    SecurityUser getSecurityUser();
    String getUsername();
    long getUserId();
}
