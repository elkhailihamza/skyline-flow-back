package org.project.skyflow.dto.mapper;

import org.mapstruct.Mapper;
import org.project.skyflow.domain.entity.Content;
import org.project.skyflow.dto.content.HomeContentDTO;

@Mapper(componentModel = "spring")
public interface ContentMapper {
    HomeContentDTO toHomeContentDTO(Content content);
}
