package org.project.skyflow.controller;

import lombok.RequiredArgsConstructor;
import org.project.skyflow.dto.HomeContentDTO;
import org.project.skyflow.service.ContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public/content")
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;

    @GetMapping("/")
    public ResponseEntity<List<HomeContentDTO>> fetchHomeContent() {
        return ResponseEntity.ok(contentService.fetchContent());
    }
}
