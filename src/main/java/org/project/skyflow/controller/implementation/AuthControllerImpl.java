package org.project.skyflow.controller.implementation;

import lombok.RequiredArgsConstructor;
import org.project.skyflow.controller.AuthController;
import org.project.skyflow.dto.AuthDTO;
import org.project.skyflow.service.AuthService;

@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    @Override
    public void login(AuthDTO authDTO) {
        authService.login(authDTO);
    }

    @Override
    public void register(AuthDTO authDTO) {
        authService.register(authDTO);
    }
}
