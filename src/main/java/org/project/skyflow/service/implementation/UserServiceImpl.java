package org.project.skyflow.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.project.skyflow.config.security.auth.AuthFacade;
import org.project.skyflow.domain.entity.User;
import org.project.skyflow.dto.ProfileDTO;
import org.project.skyflow.dto.UserPublicInfoDTO;
import org.project.skyflow.dto.mapper.UserMapper;
import org.project.skyflow.repository.FollowRepository;
import org.project.skyflow.repository.UserRepository;
import org.project.skyflow.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AuthFacade authFacade;
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public ProfileDTO viewProfile() {
        User user = userRepository.findById(authFacade.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        ProfileDTO profileDTO = userMapper.toProfileDTO(user);
        long followingCount = followRepository.countFollowingsByUserId(authFacade.getUserId());

        profileDTO.setFollowingsCount(followingCount);

        if (profileDTO.getAccount() != null) {
            long followerCount = followRepository.countFollowersByAccountId(user.getAccount().getId());
            profileDTO.getAccount().setFollowerCount(followerCount);
        }

        return profileDTO;
    }

    @Override
    public UserPublicInfoDTO viewShortDetails() {
        User user = userRepository.findById(authFacade.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));
        return userMapper.toUserPublicInfoDTO(user);
    }
}
