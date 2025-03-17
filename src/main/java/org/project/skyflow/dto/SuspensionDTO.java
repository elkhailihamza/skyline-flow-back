package org.project.skyflow.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SuspensionDTO {
    private long id;
    private UserDTO user;
    private String suspensionReason;
    private LocalDateTime suspensionEndTime;
}
