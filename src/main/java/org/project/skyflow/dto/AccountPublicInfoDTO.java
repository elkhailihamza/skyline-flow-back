package org.project.skyflow.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AccountPublicInfoDTO {
    private long id;
    private String username;
    private UserPublicInfoDTO userPublicInfoDTO;
    private String profilePicture;
    private LocalDate createdAt;
}
