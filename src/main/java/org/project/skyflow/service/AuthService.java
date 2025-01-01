package org.project.skyflow.service;

import org.project.skyflow.dto.AuthDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    void login(AuthDTO authDTO);
    void register(AuthDTO authDTO);
}
