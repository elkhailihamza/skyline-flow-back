package org.project.skyflow.controller;

import org.project.skyflow.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public interface UserController {
    ResponseEntity<UserDTO> viewProfile();
}
