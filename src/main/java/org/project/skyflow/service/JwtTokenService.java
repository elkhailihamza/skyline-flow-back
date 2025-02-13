package org.project.skyflow.service;

import org.project.skyflow.dto.AuthTokenDTO;

public interface JwtTokenService {
    void stockToken(AuthTokenDTO authTokenDTO);
    void removeExpiredTokens(AuthTokenDTO authTokenDTO);
}
