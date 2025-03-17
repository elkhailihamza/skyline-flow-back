package org.project.skyflow.dto;

import lombok.Builder;
import lombok.Data;
import org.project.skyflow.domain.entity.Content;

@Data
@Builder
public class VoteDTO {
    private long id;
    private UserDTO voter;
    private Content content;
    private boolean isUpvoted;
}
