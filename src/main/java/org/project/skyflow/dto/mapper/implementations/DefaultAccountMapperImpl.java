package org.project.skyflow.dto.mapper.implementations;

import org.project.skyflow.domain.entity.Account;
import org.project.skyflow.domain.entity.User;
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
                .build();
    }

    @Override
    public Account toAccount(AccountDTO accountDTO) {
        if (accountDTO == null) {
            return null;
        }

        return Account.builder()
                .id(accountDTO.getId())
                .user(User.builder().id(accountDTO.getUser()).build())
                .bio(accountDTO.getBio())
                .username(accountDTO.getUsername())
                .profilePicture(accountDTO.getProfilePicture())
                .createdAt(accountDTO.getCreatedAt())
                .build();
    }
}
