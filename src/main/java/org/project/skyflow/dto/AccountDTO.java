package org.project.skyflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AccountDTO {
    public interface AccountCreate {};
    public interface AccountUpdate {};

    private long id;

    @NotBlank(message = "This username shouldn't be null!", groups = {AccountCreate.class, AccountUpdate.class})
    @Pattern(
            regexp = "^[a-zA-Z][a-zA-Z0-9_]{2,29}$",
            message = "Username must start with a letter, contain only letters, numbers, or underscores, and be 3-30 characters long.",
            groups = {AccountCreate.class, AccountUpdate.class}
    )
    private String username;

    private Long user;

    @Pattern(
            regexp = "^\\s*$|^.{1,275}$",
            message = "The account bio must be blank or up to 275 characters long!",
            groups = {AccountCreate.class, AccountUpdate.class}
    )
    private String bio;

    private String profilePicture;

    private LocalDate createdAt;

    private long followerCount;

    private long contentCount;
}
