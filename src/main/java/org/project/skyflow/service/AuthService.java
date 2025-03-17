package org.project.skyflow.service;

import jakarta.servlet.http.HttpServletResponse;
import org.project.skyflow.config.security.SecurityUser;
import org.project.skyflow.dto.AuthDTO;
import org.project.skyflow.dto.AuthTokenDTO;
import org.project.skyflow.dto.UserDTO;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    AuthTokenDTO login(AuthDTO authDTO);
    AuthTokenDTO register(AuthDTO authDTO);
    AuthTokenDTO generateResponseInfo(SecurityUser securityUser);
    AuthTokenDTO refreshTokens(AuthTokenDTO authTokenDTO);
    UserDTO getCurrentUser(AuthTokenDTO authTokenDTO);
}
