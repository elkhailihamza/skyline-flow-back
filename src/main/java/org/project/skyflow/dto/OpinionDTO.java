package org.project.skyflow.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Builder
@Data
public class OpinionDTO {
    public interface OpinionCreate {};
    public interface OpinionUpdate {};

    private long id;

    @NotBlank(message = "Text shouldn't be empty", groups = {OpinionCreate.class, OpinionUpdate.class})
    @Length(message = "This shouldn't surpass 500 characters", max = 500, groups = {OpinionCreate.class, OpinionUpdate.class})
    private String text;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private long creator;
    private long content;
}
