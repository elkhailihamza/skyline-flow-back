package org.project.skyflow.controller;

import lombok.RequiredArgsConstructor;
import org.project.skyflow.dto.HomeContentDTO;
import org.project.skyflow.service.ContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content")
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;

    @GetMapping("/")
    public ResponseEntity<List<HomeContentDTO>> fetchHomeContent() {
        return ResponseEntity.ok(contentService.fetchContent());
    }

    @PostMapping("/create")
    public ResponseEntity<String> uploadContent() {
        contentService.createContent();
        return ResponseEntity.ok("test");
    }

//    @PostMapping("/assign/category")
//    public ResponseEntity<String> assignToCategory(Category) {
//
//    }
}