package org.project.skyflow.controller;

import lombok.RequiredArgsConstructor;
import org.project.skyflow.dto.ProfileDTO;
import org.project.skyflow.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<ProfileDTO> viewProfile() {
        ProfileDTO profileDTO = userService.viewProfile();
        return ResponseEntity.ok(profileDTO);
    }
}
