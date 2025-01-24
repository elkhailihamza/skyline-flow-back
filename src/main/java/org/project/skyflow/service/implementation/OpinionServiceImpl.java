package org.project.skyflow.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.project.skyflow.config.security.auth.AuthFacade;
import org.project.skyflow.domain.entity.Account;
import org.project.skyflow.domain.entity.Content;
import org.project.skyflow.domain.entity.Opinion;
import org.project.skyflow.dto.OpinionDTO;
import org.project.skyflow.dto.mapper.OpinionMapper;
import org.project.skyflow.exception.ItemNotOwnedException;
import org.project.skyflow.repository.AccountRepository;
import org.project.skyflow.repository.ContentRepository;
import org.project.skyflow.repository.OpinionRepository;
import org.project.skyflow.service.OpinionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OpinionServiceImpl implements OpinionService {
    private final OpinionRepository opinionRepository;
    private final AccountRepository accountRepository;
    private final ContentRepository contentRepository;
    private final OpinionMapper opinionMapper;
    private final AuthFacade auth;

    @Override
    @Transactional
    public OpinionDTO addOpinion(long contentId, OpinionDTO opinionDTO) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new EntityNotFoundException("This content does not exist!"));
        Account account = accountRepository.findAccountByUserId(auth.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("This account doesn't exist!"));

        Opinion opinion = Opinion.builder()
                .text(opinionDTO.getText())
                .content(content)
                .creator(account)
                .createdAt(LocalDateTime.now())
                .build();

        return opinionMapper.toOpinionDTO(opinionRepository.save(opinion));
    }

    @Override
    public OpinionDTO updateOpinion(long opinionId, OpinionDTO opinionDTO) {
        if (!opinionRepository.existsByIdAndCreatorId(opinionId, auth.getUserId())) {
            throw new ItemNotOwnedException("This opinion isn't owned by user!");
        }

        Opinion opinion = opinionRepository.findById(opinionId)
                .orElseThrow(() -> new EntityNotFoundException("This opinion does not exist!"));

        if (Objects.equals(opinionDTO.getText(), opinion.getText())) {
            return opinionMapper.toOpinionDTO(opinion);
        }

        opinion.setText(opinionDTO.getText());
        opinion.setUpdatedAt(LocalDateTime.now());

        return opinionMapper.toOpinionDTO(opinionRepository.save(opinion));
    }

    @Override
    public void deleteOpinion(long opinionId) {
        if (!opinionRepository.existsByIdAndCreatorId(opinionId, auth.getUserId())) {
            throw new ItemNotOwnedException("This opinion isn't owned by user!");
        }

        Opinion opinion = opinionRepository.findById(opinionId)
                .orElseThrow(() -> new EntityNotFoundException("This opinion does not exist!"));
        opinionRepository.delete(opinion);
    }
}
