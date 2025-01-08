package org.project.skyflow.controller.implementation;

import lombok.RequiredArgsConstructor;
import org.project.skyflow.controller.UserController;
import org.project.skyflow.dto.UserDTO;
import org.project.skyflow.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @Override
    public ResponseEntity<UserDTO> viewProfile() {

        return null;
    }
}
