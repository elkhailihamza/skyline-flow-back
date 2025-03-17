package org.project.skyflow.service;

import org.project.skyflow.dto.ProfileDTO;
import org.project.skyflow.dto.UserPublicInfoDTO;

public interface UserService {
    ProfileDTO viewProfile();
    UserPublicInfoDTO viewShortDetails();
}
