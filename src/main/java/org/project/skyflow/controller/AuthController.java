package org.project.skyflow.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.project.skyflow.config.security.jwt.JwtProvider;
import org.project.skyflow.dto.AuthDTO;
import org.project.skyflow.dto.AuthTokenDTO;
import org.project.skyflow.dto.UserDTO;
import org.project.skyflow.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Validated(AuthDTO.AuthLogin.class) AuthDTO authDTO, HttpServletResponse response) {
        AuthTokenDTO authTokenDTO = authService.login(authDTO);
        jwtProvider.setCookies(authTokenDTO, response);
        return ResponseEntity.ok("logged in!");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Validated(AuthDTO.AuthRegister.class) AuthDTO authDTO, HttpServletResponse response) {
        AuthTokenDTO authTokenDTO = authService.register(authDTO);
        jwtProvider.setCookies(authTokenDTO, response);
        return ResponseEntity.ok("created user!");
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refresh(@CookieValue(name = "refreshToken", required = false) String refreshToken, HttpServletResponse response) {
        AuthTokenDTO authTokenDTO = authService.refreshTokens(AuthTokenDTO.builder().jwtRefreshToken(refreshToken).build());
        jwtProvider.setCookies(authTokenDTO, response);
        return ResponseEntity.ok("token reset!");
    }

    @GetMapping("/user")
    public ResponseEntity<UserDTO> getCurrentUser(@CookieValue(name = "jwt") String jwtToken) {
        AuthTokenDTO authTokenDTO = AuthTokenDTO.builder().jwtToken(jwtToken).build();
        return ResponseEntity.ok(authService.getCurrentUser(authTokenDTO));
    }
}