package org.project.skyflow.repository;

import org.project.skyflow.domain.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    @Query("SELECT COUNT(f) FROM Follow f WHERE f.following.id = :accountId")
    long countFollowersByAccountId(@Param("accountId") long accountId);

    @Query("SELECT COUNT(f) FROM Follow f WHERE f.follower.id = :userId")
    long countFollowingsByUserId(@Param("userId") long userId);

}
