package org.project.skyflow.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AuthTokenDTO {
    String jwtToken;
    String jwtRefreshToken;
    Date jwtExpDate;
    Date jwtRefreshExpDate;
}
