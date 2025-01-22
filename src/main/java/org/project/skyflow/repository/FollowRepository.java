package org.project.skyflow.repository;

import org.project.skyflow.domain.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    @Query("SELECT COUNT(f) FROM Follow f WHERE f.following.id = :accountId")
    long countFollowersByAccountId(@Param("accountId") long accountId);

    @Query("SELECT COUNT(f) FROM Follow f WHERE f.follower.id = :userId")
    long countFollowingsByUserId(@Param("userId") long userId);

    @Query("SELECT f FROM Follow f WHERE f.following.id = :accountId AND f.follower.id = :userId")
    Optional<Follow> findByFollowingId(@Param("accountId") long accountId, @Param("userId") long userId);

    boolean existsByFollowerIdAndFollowingId(long follower_id, long following_id);
}
