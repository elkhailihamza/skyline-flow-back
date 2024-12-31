package org.project.skyflow.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthDTO {
    private interface AuthLogin {}
    private interface AuthRegister {}

    @NotNull(message = "Name cannot be null!", groups = AuthRegister.class)
    private String name;

    @NotNull(message = "Surname cannot be null!", groups = AuthRegister.class)
    private String surname;

    @Email(message = "Email needs to be valid!", groups = AuthRegister.class)
    @NotNull(message = "Email cannot be null!", groups = {AuthRegister.class, AuthLogin.class})
    private String email;

    @NotNull(message = "Password cannot be null!", groups = {AuthRegister.class, AuthLogin.class})
    @Size(min = 8, message = "Password must be at least 8 characters long!", groups = AuthRegister.class)
    @Pattern(
            regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
            message = "Password must include at least one uppercase letter, one lowercase letter, one number, and one special character from the following [#, ?, !, @, $, %, ^, &, *, -]!",
            groups = AuthRegister.class
    )
    private String password;

    @NotNull(message = "Confirm password cannot be null!", groups = AuthRegister.class)
    private String confirmPassword;
}
