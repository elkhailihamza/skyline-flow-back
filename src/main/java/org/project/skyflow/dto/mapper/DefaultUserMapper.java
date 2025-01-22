package org.project.skyflow.dto.mapper;

import org.project.skyflow.domain.entity.User;
import org.project.skyflow.dto.ProfileDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DefaultUserMapper {
    ProfileDTO toProfileDTO(User user);
}
