package org.project.skyflow.service.implementation;

import lombok.RequiredArgsConstructor;
import org.project.skyflow.dto.HomeContentDTO;
import org.project.skyflow.dto.mapper.ContentMapper;
import org.project.skyflow.repository.ContentRepository;
import org.project.skyflow.service.ContentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {
    private final ContentRepository contentRepository;
    private final ContentMapper contentMapper;

    @Override
    public List<HomeContentDTO> fetchContent() {
        return contentRepository.findAll().stream().map(contentMapper::toHomeContentDTO).toList();
    }
}
