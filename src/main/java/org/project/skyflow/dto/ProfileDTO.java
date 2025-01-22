package org.project.skyflow.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ProfileDTO {
    private long id;
    private String name;
    private String surname;
    private String email;
    private List<String> roles;
    private AccountDTO account;
    private long followingsCount;
    private LocalDate createdAt;
}