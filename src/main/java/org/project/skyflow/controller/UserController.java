package org.project.skyflow.controller;

import org.project.skyflow.dto.ProfileDTO;
import org.springframework.http.ResponseEntity;

public interface UserController {
    ResponseEntity<ProfileDTO> viewProfile();
}
