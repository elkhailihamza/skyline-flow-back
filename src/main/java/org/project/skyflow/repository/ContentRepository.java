package org.project.skyflow.repository;

import org.project.skyflow.domain.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

    @Query("SELECT COUNT(c) FROM Content c WHERE c.creator.id = :accountId")
    long countContentByAccountId(@Param("accountId") long accountId);
}
