package org.project.skyflow.service.implementation;

import lombok.RequiredArgsConstructor;
import org.project.skyflow.domain.entity.User;
import org.project.skyflow.dto.AuthDTO;
import org.project.skyflow.exception.EmailAlreadyExistsException;
import org.project.skyflow.repository.UserRepository;
import org.project.skyflow.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapp

    @Override
    public void login(AuthDTO authDTO) {

    }

    @Override
    public void register(AuthDTO authDTO) {
        String email = authDTO.getEmail();
        if (repository.existsUserByEmail(email)) {
            throw new EmailAlreadyExistsException("Email already exists!");
        }

        String encodedPassword = passwordEncoder.encode(authDTO.getPassword());
        User user =
    }
}
