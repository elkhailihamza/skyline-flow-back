package org.project.skyflow.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.project.skyflow.config.security.auth.AuthFacade;
import org.project.skyflow.domain.entity.Account;
import org.project.skyflow.domain.entity.Follow;
import org.project.skyflow.domain.entity.User;
import org.project.skyflow.exception.ItemAlreadyExistsException;
import org.project.skyflow.exception.WhyWouldYouDoThisException;
import org.project.skyflow.repository.AccountRepository;
import org.project.skyflow.repository.FollowRepository;
import org.project.skyflow.service.FollowService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {
    private final FollowRepository followRepository;
    private final AccountRepository accountRepository;
    private final AuthFacade auth;

    @Override
    public void followAccount(long accountId) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("This account does not exist!"));

        if (account.getUser().getId() == auth.getUserId()) {
            throw new WhyWouldYouDoThisException("You can't follow yourself!");
        }

        if (followRepository.existsByFollowerIdAndFollowingId(auth.getUserId(), accountId)) {
            throw new ItemAlreadyExistsException("User already following this account!");
        }

        followRepository.save(Follow.builder()
                .follower(User.builder()
                        .id(auth.getUserId())
                        .build())
                .following(Account.builder()
                        .id(accountId)
                        .build())
                .build());
    }
}
