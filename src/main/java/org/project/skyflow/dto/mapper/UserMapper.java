package org.project.skyflow.dto.mapper;

import org.mapstruct.Mapper;
import org.project.skyflow.domain.entity.User;
import org.project.skyflow.dto.AuthDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(AuthDTO authDTO);
}