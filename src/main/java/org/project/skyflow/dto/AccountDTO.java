package org.project.skyflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class AccountDTO {
    public interface AccountCreate {};

    private long id;

    @NotBlank(message = "This username shouldn't be null!", groups = {AccountCreate.class})
    private String username;

    private Long user;

    private String bio;

    private String profilePicture;

    private LocalDate createdAt;

    private long followerCount;

    private long contentCount;
}
