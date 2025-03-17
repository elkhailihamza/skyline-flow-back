package org.project.skyflow.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.project.skyflow.config.security.SecurityUser;
import org.project.skyflow.config.security.jwt.JwtProvider;
import org.project.skyflow.domain.entity.Role;
import org.project.skyflow.domain.entity.User;
import org.project.skyflow.dto.AuthDTO;
import org.project.skyflow.dto.AuthTokenDTO;
import org.project.skyflow.dto.UserDTO;
import org.project.skyflow.dto.mapper.UserMapper;
import org.project.skyflow.exception.EmailAlreadyExistsException;
import org.project.skyflow.repository.RoleRepository;
import org.project.skyflow.repository.UserRepository;
import org.project.skyflow.service.AuthService;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    @Override
    public AuthTokenDTO login(AuthDTO authDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();

        return generateResponseInfo(securityUser);
    }

    @Override
    public AuthTokenDTO register(AuthDTO authDTO) {
        String email = authDTO.getEmail();
        if (repository.existsUserByEmail(email)) {
            throw new EmailAlreadyExistsException("Email already exists!");
        }

        String encodedPassword = passwordEncoder.encode(authDTO.getPassword());
        User user = userMapper.toUser(authDTO);
        user.setPassword(encodedPassword);

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new EntityNotFoundException("Role not found!"));
        user.setRoles(List.of(userRole));

        repository.save(user);

        return this.login(authDTO);
    }

    @Override
    public AuthTokenDTO generateResponseInfo(SecurityUser securityUser) {
        String jwtToken = jwtProvider.generateTokenFromUsername(securityUser);
        String refreshToken = jwtProvider.generateRefreshTokenFromUsername(securityUser);
        return AuthTokenDTO.builder()
                .jwtToken(jwtToken)
                .jwtRefreshToken(refreshToken)
                .jwtExpDate(jwtProvider.getExpirationDate(false))
                .jwtRefreshExpDate(jwtProvider.getExpirationDate(true))
                .build();
    }

    @Override
    public AuthTokenDTO refreshTokens(AuthTokenDTO authTokenDTO) {
        String refreshToken = authTokenDTO.getJwtRefreshToken();

        if (!jwtProvider.validateJwtToken(refreshToken, true)) {
            throw new SecurityException("Invalid or expired refresh token");
        }

        String username = jwtProvider.getUserNameFromJwtToken(refreshToken, true);

        User user = repository.findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        SecurityUser securityUser = new SecurityUser(user);
        return generateResponseInfo(securityUser);
    }

    @Override
    public UserDTO getCurrentUser(AuthTokenDTO authTokenDTO) {
        return null;
    }
}
