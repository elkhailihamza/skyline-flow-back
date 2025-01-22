package org.project.skyflow.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.project.skyflow.domain.entity.Account;
import org.project.skyflow.dto.AccountDTO;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(source = "user.id", target = "user")
    @Mapping(target = "followerCount", ignore = true)
    @Mapping(target = "contentCount", ignore = true)
    AccountDTO toAccountDTO(Account account);

}
