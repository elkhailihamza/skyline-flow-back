package org.project.skyflow.dto.mapper.implementations;

import lombok.RequiredArgsConstructor;
import org.project.skyflow.domain.entity.*;
import org.project.skyflow.dto.AccountDTO;
import org.project.skyflow.dto.ProfileDTO;
import org.project.skyflow.dto.mapper.DefaultAccountMapper;
import org.project.skyflow.dto.mapper.DefaultUserMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class DefaultUserMapperImpl implements DefaultUserMapper {
    private final DefaultAccountMapper accountMapper;

    @Override
    public ProfileDTO toProfileDTO(User user) {
        if (user == null) {
            return null;
        }

        ProfileDTO.ProfileDTOBuilder profileDTOBuilder = ProfileDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .roles(user.getRoles().stream().map(Role::getName).toList());

        if (user.getAccount() != null) {
            AccountDTO accountDTO = accountMapper.toAccountDTO(user.getAccount());
            profileDTOBuilder.account(accountDTO);
        }

        return profileDTOBuilder.build();
    }
}
