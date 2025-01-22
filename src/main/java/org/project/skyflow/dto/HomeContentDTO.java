package org.project.skyflow.dto;

import org.project.skyflow.domain.entity.*;

import java.time.LocalDateTime;

public class HomeContentDTO {
    private long id;
    private String title;
    private long creator;
    private LocalDateTime uploadDate;
    private ContentMetaData contentMetaData;
}
