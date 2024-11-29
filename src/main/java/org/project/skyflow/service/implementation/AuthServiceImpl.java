package org.project.skyflow.service.implementation;

import lombok.RequiredArgsConstructor;
import org.project.skyflow.dto.AuthDTO;
import org.project.skyflow.repository.UserRepository;
import org.project.skyflow.service.AuthService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;

    @Override
    public void Register(AuthDTO authDTO) {
        
    }

    @Override
    public void Login(AuthDTO authDTO) {

    }
}
