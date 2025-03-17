package org.project.skyflow.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPublicInfoDTO {
    private long id;
    private String name;
    private String surname;
    private String email;
    private AccountPublicInfoDTO accountPublicInfoDTO;
}
