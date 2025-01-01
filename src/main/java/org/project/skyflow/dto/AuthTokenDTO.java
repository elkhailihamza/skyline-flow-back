package org.project.skyflow.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AuthTokenDTO {
    String jwtToken;
    String jwtRefreshToken;
    Date expDate;
}
