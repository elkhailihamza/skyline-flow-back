package org.project.skyflow.service.implementation;

import lombok.RequiredArgsConstructor;
import org.project.skyflow.config.security.jwt.JwtProvider;
import org.project.skyflow.domain.entity.JwtToken;
import org.project.skyflow.dto.AuthTokenDTO;
import org.project.skyflow.repository.JwtTokenRepository;
import org.project.skyflow.service.JwtTokenService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {
    private final JwtTokenRepository jwtTokenRepository;
    private final JwtProvider jwtProvider;

    @Override
    public void stockToken(AuthTokenDTO authTokenDTO) {
        JwtToken jwtToken = JwtToken.builder()
                .token(authTokenDTO.getJwtRefreshToken())
                .expDate(jwtProvider.getExpirationDate(true))
                .build();

        if (!jwtTokenRepository.existsByToken(jwtToken.getToken())) {
            jwtTokenRepository.save(jwtToken);
        }
    }

    @Override
    @Scheduled(cron = "0 * * * * *")
    public void removeExpiredTokens(AuthTokenDTO authTokenDTO) {
        Date now = new Date();

        List<JwtToken> expiredTokens = jwtTokenRepository.findByExpDateGreaterThan(now);

        if (!expiredTokens.isEmpty()) {
            jwtTokenRepository.deleteAll(expiredTokens);
        }
    }
}
