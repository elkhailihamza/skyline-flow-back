package org.project.skyflow.dto;

import lombok.Builder;
import lombok.Data;
import org.project.skyflow.domain.entity.*;

import java.time.LocalDateTime;

@Data
@Builder
public class HomeContentDTO {
    private long id;
    private String title;
    private AccountDTO creator;
    private LocalDateTime uploadDate;
    private ContentMetaData contentMetaData;
}
