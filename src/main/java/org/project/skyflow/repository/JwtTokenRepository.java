package org.project.skyflow.repository;

import org.project.skyflow.domain.entity.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {
    boolean existsByToken(String token);

    @Query("SELECT t FROM JwtToken t WHERE t.expDate > :expiryDate")
    List<JwtToken> findByExpDateGreaterThan(@Param("expiryDate") Date expiryDate);
}
