package org.project.skyflow.config.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.project.skyflow.config.security.SecurityUser;
import org.project.skyflow.exception.JwtValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    @Value("${spring.application.jwt.jwtSecret}")
    private String jwtSecret;

    @Value("${spring.application.jwt.jwtRefreshSecret}")
    private String jwtRefreshSecret;

    @Value("${spring.application.jwt.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${spring.application.jwt.jwtRefreshTokenExpirationMs}")
    private int jwtRefreshTokenExpirationMs;

    public String getJwtFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String generateRefreshTokenFromUsername(UserDetails userDetails) {
        String username = userDetails.getUsername();
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtRefreshTokenExpirationMs))
                .signWith(key(jwtRefreshSecret))
                .compact();
    }

    public String generateTokenFromUsername(SecurityUser securityUser) {
        String username = securityUser.getUsername();
        long id = securityUser.getId();
        Collection<? extends GrantedAuthority> roles = securityUser.getAuthorities();

        List<String> roleNames = roles.stream()
                .map(GrantedAuthority::getAuthority)
                .map(role -> role.replace("ROLE_", ""))
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("roles", roleNames);

        return Jwts.builder()
                .subject(username)
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(jwtSecret))
                .compact();
    }

    public String getUserNameFromJwtToken(String token, boolean isRefreshToken) {
        Key key = isRefreshToken ? key(jwtRefreshSecret) : key(jwtSecret);
        return Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    private Key key(String secret) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public boolean validateJwtToken(String authToken, boolean isRefreshToken) throws ExpiredJwtException {
        Key key = isRefreshToken ? key(jwtRefreshSecret) : key(jwtSecret);
        try {
            Jwts.parser().verifyWith((SecretKey) key).build().parseSignedClaims(authToken);
            return true;
        } catch (ExpiredJwtException e) {
            throw new JwtValidationException("JWT token has expired: " + e.getMessage());
        } catch (MalformedJwtException e) {
            throw new JwtValidationException("Malformed JWT token: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            throw new JwtValidationException("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new JwtValidationException("JWT claims string is empty: " + e.getMessage());
        }
    }

    public boolean isJwtExpired(String authToken, boolean isRefreshToken) {
        Key key = isRefreshToken ? key(jwtRefreshSecret) : key(jwtSecret);
        try {
            Claims claims = Jwts.parser()
                    .verifyWith((SecretKey) key)
                    .build()
                    .parseSignedClaims(authToken)
                    .getPayload();

            Date expirationDate = claims.getExpiration();
            return expirationDate.before(new Date());
        } catch (JwtException e) {
            throw new JwtValidationException("Error checking token expiration: " + e.getMessage());
        }
    }

    public Date getExpirationDate() {
        return new Date((new Date()).getTime() + jwtExpirationMs);
    }
}
