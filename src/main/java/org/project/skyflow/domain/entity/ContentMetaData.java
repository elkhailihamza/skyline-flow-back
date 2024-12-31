package org.project.skyflow.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.project.skyflow.domain.entity.type.ProcessingStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContentMetaData {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    private Content content;

    private String filePath;
    private long fileSize;
    private String thumbnail;
    private double duration;
    private String resolution;
    private String codec;
    private ProcessingStatus processingStatus;
    private long plays;
    private long upVotes;
    private long downVotes;
    private LocalDateTime lastProcessedDate;
    private String mimeType;
    private String storageLocation;
}
