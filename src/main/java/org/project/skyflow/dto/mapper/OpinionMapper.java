package org.project.skyflow.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.project.skyflow.domain.entity.Opinion;
import org.project.skyflow.dto.OpinionDTO;

@Mapper(componentModel = "spring")
public interface OpinionMapper {

    @Mapping(source = "creator.id", target = "creator")
    @Mapping(source = "content.id", target = "content")
    OpinionDTO toOpinionDTO(Opinion opinion);

    @Mapping(source = "creator", target = "creator.id")
    @Mapping(source = "content", target = "content.id")
    Opinion toOpinion(OpinionDTO opinionDTO);
}
