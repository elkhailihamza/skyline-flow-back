package org.project.skyflow.dto.mapper.implementations;

import org.project.skyflow.domain.entity.Account;
import org.project.skyflow.domain.entity.Content;
import org.project.skyflow.domain.entity.Follow;
import org.project.skyflow.dto.AccountDTO;
import org.project.skyflow.dto.mapper.DefaultAccountMapper;
import org.springframework.stereotype.Component;

@Component
public class DefaultAccountMapperImpl implements DefaultAccountMapper {
    @Override
    public AccountDTO toAccountDTO(Account account) {
        if (account == null) {
            return null;
        }

        return AccountDTO.builder()
                .id(account.getId())
                .user(account.getUser().getId())
                .bio(account.getBio())
                .username(account.getUsername())
                .profilePicture(account.getProfilePicture())
                .createdAt(account.getCreatedAt())
                .followers(account.getFollowers().stream().map(Follow::getId).toList())
                .contentList(account.getContentList().stream().map(Content::getId).toList())
                .build();
    }
}
