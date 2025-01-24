package org.project.skyflow.controller;

import lombok.RequiredArgsConstructor;
import org.project.skyflow.dto.OpinionDTO;
import org.project.skyflow.service.OpinionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/opinion")
@RequiredArgsConstructor
public class OpinionController {
    private final OpinionService opinionService;

    @PostMapping("/create/{contentId}")
    public ResponseEntity<OpinionDTO> addOpinion(@PathVariable long contentId, @RequestBody @Validated(OpinionDTO.OpinionCreate.class) OpinionDTO opinionDTO) {
        return ResponseEntity.ok(opinionService.addOpinion(contentId, opinionDTO));
    }

    @PostMapping("/update/{opinionId}")
    public ResponseEntity<OpinionDTO> updateOpinion(@PathVariable long opinionId, @RequestBody @Validated(OpinionDTO.OpinionUpdate.class) OpinionDTO opinionDTO) {
        return ResponseEntity.ok(opinionService.updateOpinion(opinionId, opinionDTO));
    }

    @DeleteMapping("/delete/{opinionId}")
    public ResponseEntity<String> deleteOpinion(@PathVariable long opinionId) {
        opinionService.deleteOpinion(opinionId);
        return ResponseEntity.ok("Deleted opinion successfully!");
    }
}
