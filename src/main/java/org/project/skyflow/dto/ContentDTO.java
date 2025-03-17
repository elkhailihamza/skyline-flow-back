package org.project.skyflow.dto;

import lombok.Builder;
import lombok.Data;
import org.project.skyflow.domain.entity.*;
import org.project.skyflow.domain.entity.type.ContentStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class ContentDTO {
    private long id;
    private String title;
    private String description;
    private Account creator;
    private LocalDateTime uploadDate;
    private ContentStatus status;
    private Category category;
    private ContentMetaData contentMetaData;
    private List<Opinion> opinions;
    private List<Vote> votes;
    private Set<Playlist> playlists;
}
