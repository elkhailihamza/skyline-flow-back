package org.project.skyflow.dto;

import lombok.Builder;
import lombok.Data;
import org.project.skyflow.domain.entity.User;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class AccountDTO {
    private long id;
    private String username;
    private Long user;
    private String bio;
    private String profilePicture;
    private LocalDate createdAt;
    private List<Long> followers;
    private List<Long> contentList;
}
