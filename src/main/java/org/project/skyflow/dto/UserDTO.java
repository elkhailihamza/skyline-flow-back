package org.project.skyflow.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDTO {
    private long id;
    private String name;
    private String surname;
    private String email;
    private List<SuspensionDTO> suspensions;
    private boolean isSuspended;
    private boolean isActive;
    private List<RoleDTO> roles;
    private AccountDTO account;
}
