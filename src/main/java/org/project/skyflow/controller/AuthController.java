package org.project.skyflow.controller;

import org.project.skyflow.dto.AuthDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface AuthController {

    @PostMapping("/login")
    void login(@RequestBody @Validated(AuthDTO.AuthLogin.class) AuthDTO authDTO);

    @PostMapping("/register")
    void register(@RequestBody @Validated(AuthDTO.AuthRegister.class) AuthDTO authDTO);
}
