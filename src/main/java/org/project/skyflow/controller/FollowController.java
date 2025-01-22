package org.project.skyflow.controller;

import lombok.RequiredArgsConstructor;
import org.project.skyflow.service.FollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow")
public class FollowController {
    private final FollowService followService;

    @PostMapping("/{accountId}")
    public ResponseEntity<String> sendFollow(@PathVariable long accountId) {
        followService.followAccount(accountId);
        return ResponseEntity.ok("Followed account successfully!");
    }
}
