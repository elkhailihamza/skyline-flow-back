package org.project.skyflow.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.project.skyflow.config.security.auth.AuthFacade;
import org.project.skyflow.domain.entity.Account;
import org.project.skyflow.domain.entity.User;
import org.project.skyflow.dto.AccountDTO;
import org.project.skyflow.dto.mapper.AccountMapper;
import org.project.skyflow.exception.AccountAlreadyExistsException;
import org.project.skyflow.exception.ItemNotOwnedException;
import org.project.skyflow.repository.AccountRepository;
import org.project.skyflow.repository.ContentRepository;
import org.project.skyflow.repository.FollowRepository;
import org.project.skyflow.service.AccountService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AuthFacade auth;
    private final AccountMapper accountMapper;
    private final FollowRepository followRepository;
    private final ContentRepository contentRepository;

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        if (accountRepository.existsAccountByUser_Email(auth.getUserName())) {
            throw new AccountAlreadyExistsException("User already has an account!");
        }

        if (accountRepository.existsAccountByUsername(accountDTO.getUsername())) {
            throw new AccountAlreadyExistsException("Username already taken!");
        }
        Account account = Account.builder()
                .username(accountDTO.getUsername())
                .profilePicture(accountDTO.getProfilePicture())
                .user(User.builder().id(auth.getUserId()).build())
                .createdAt(LocalDate.now())
                .build();

        return accountMapper.toAccountDTO(accountRepository.save(account));
    }

    @Override
    public AccountDTO fetchAccount(long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account not found!"));

        AccountDTO accountDTO = accountMapper.toAccountDTO(account);

        long followerCount = followRepository.countFollowersByAccountId(account.getId());
        long contentCount = contentRepository.countContentByAccountId(account.getId());

        accountDTO.setFollowerCount(followerCount);
        accountDTO.setContentCount(contentCount);

        return accountDTO;
    }

    @Override
    public AccountDTO updateAccount(long accountId, AccountDTO accountDTO) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found!"));

        if (account.getUser().getId() != auth.getUserId()) {
            throw new ItemNotOwnedException("Account not owned by user!");
        }

        account.setUsername(Optional.ofNullable(accountDTO.getUsername()).orElse(account.getUsername()));
        account.setBio(Optional.ofNullable(accountDTO.getBio()).orElse(account.getBio()));
        account.setProfilePicture(Optional.ofNullable(accountDTO.getProfilePicture()).orElse(account.getProfilePicture()));

        return accountMapper.toAccountDTO(accountRepository.save(account));
    }


}
