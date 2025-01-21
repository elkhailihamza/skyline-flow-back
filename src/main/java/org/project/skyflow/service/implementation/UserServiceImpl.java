package org.project.skyflow.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.project.skyflow.config.security.auth.AuthFacade;
import org.project.skyflow.domain.entity.User;
import org.project.skyflow.dto.ProfileDTO;
import org.project.skyflow.dto.mapper.DefaultUserMapper;
import org.project.skyflow.dto.mapper.UserMapper;
import org.project.skyflow.repository.UserRepository;
import org.project.skyflow.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AuthFacade authFacade;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final DefaultUserMapper defaultUserMapper;

    @Override
    public ProfileDTO viewProfile() {
        User user = userRepository.findById(authFacade.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));
        return defaultUserMapper.toProfileDTO(user);
    }
}
