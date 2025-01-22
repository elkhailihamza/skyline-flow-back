package org.project.skyflow.service;

import org.project.skyflow.dto.HomeContentDTO;

import java.util.List;

public interface ContentService {
    List<HomeContentDTO> fetchContent();
}
