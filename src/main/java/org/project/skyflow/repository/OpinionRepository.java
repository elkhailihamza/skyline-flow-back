package org.project.skyflow.repository;

import org.project.skyflow.domain.entity.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Long> {

    @Query("SELECT o FROM Opinion o WHERE o.content.id = :contentId AND o.creator.id = :creatorId")
    boolean existsByContentIdAndCreatorId(@Param("contentId") long contentId, @Param("creatorId") long creatorId);

    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN TRUE ELSE FALSE END FROM Opinion o WHERE o.id = :opinionId AND o.creator.id = :creatorId")
    boolean existsByIdAndCreatorId(@Param("opinionId") long opinionId, @Param("creatorId") long creatorId);
}
