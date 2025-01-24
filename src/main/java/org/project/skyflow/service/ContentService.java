package org.project.skyflow.service;

import org.project.skyflow.dto.HomeContentDTO;

import java.io.File;
import java.util.List;

public interface ContentService {
    List<HomeContentDTO> fetchContent();
    void createContent();

    void processContent(File videoFile);
    void convertContentFormat(File inputFile, File outputFile);
}
