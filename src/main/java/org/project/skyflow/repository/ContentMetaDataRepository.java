package org.project.skyflow.repository;

import org.project.skyflow.entity.ContentMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentMetaDataRepository extends JpaRepository<ContentMetaData, Long> {
}
