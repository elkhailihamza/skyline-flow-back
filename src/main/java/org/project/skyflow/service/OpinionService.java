package org.project.skyflow.service;

import org.project.skyflow.dto.OpinionDTO;

public interface OpinionService {
    OpinionDTO addOpinion(long contentId, OpinionDTO opinionDTO);
    OpinionDTO updateOpinion(long opinionId, OpinionDTO opinionDTO);
    void deleteOpinion(long opinionId);
}
