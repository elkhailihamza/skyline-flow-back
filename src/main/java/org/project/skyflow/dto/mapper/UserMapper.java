package org.project.skyflow.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.project.skyflow.domain.entity.User;
import org.project.skyflow.dto.AuthDTO;
import org.project.skyflow.dto.ProfileDTO;
import org.project.skyflow.dto.UserDTO;
import org.project.skyflow.dto.UserPublicInfoDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(AuthDTO authDTO);

    @Mapping(target = "suspensions.user", ignore = true)
    @Mapping(target = "account.user", ignore = true)
    UserDTO toDTO(User user);

    @Mapping(target = "roles", source = "roles")
    ProfileDTO toProfileDTO(User user);

    @Mapping(target = "accountPublicInfoDTO.userPublicInfoDTO", ignore = true)
    UserPublicInfoDTO toUserPublicInfoDTO(User user);
}