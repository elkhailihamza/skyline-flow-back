package org.project.skyflow.dto;

import lombok.Data;
import org.project.skyflow.entity.type.Role;

@Data
public class AuthDTO {
    private long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Role role;
}
