package org.project.skyflow.controller;

import org.project.skyflow.dto.AuthDTO;
import org.project.skyflow.dto.AuthTokenDTO;
import org.springframework.http.ResponseEntity;

public interface AuthController {
    ResponseEntity<AuthTokenDTO> login(AuthDTO authDTO);
    ResponseEntity<AuthTokenDTO> register(AuthDTO authDTO);
}
