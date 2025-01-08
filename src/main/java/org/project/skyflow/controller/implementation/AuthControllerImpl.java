package org.project.skyflow.controller.implementation;

import lombok.RequiredArgsConstructor;
import org.project.skyflow.controller.AuthController;
import org.project.skyflow.dto.AuthDTO;
import org.project.skyflow.dto.AuthTokenDTO;
import org.project.skyflow.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    @Override
    @PostMapping("/login")
    public ResponseEntity<AuthTokenDTO> login(@RequestBody @Validated(AuthDTO.AuthLogin.class) AuthDTO authDTO) {
        AuthTokenDTO authTokenDTO = authService.login(authDTO);
        return ResponseEntity.ok(authTokenDTO);
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<AuthTokenDTO> register(@RequestBody @Validated(AuthDTO.AuthRegister.class) AuthDTO authDTO) {
        AuthTokenDTO authTokenDTO = authService.register(authDTO);
        return ResponseEntity.ok(authTokenDTO);
    }
}
