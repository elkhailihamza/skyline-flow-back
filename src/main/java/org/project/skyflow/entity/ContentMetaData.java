package org.project.skyflow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.project.skyflow.entity.type.ProcessingStatus;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContentMetaData {
    @Id
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
