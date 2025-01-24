package org.project.skyflow.service.implementation;

import lombok.RequiredArgsConstructor;
import org.project.skyflow.domain.entity.Account;
import org.project.skyflow.domain.entity.Category;
import org.project.skyflow.domain.entity.Content;
import org.project.skyflow.domain.entity.ContentMetaData;
import org.project.skyflow.domain.entity.type.ContentStatus;
import org.project.skyflow.domain.entity.type.ProcessingStatus;
import org.project.skyflow.dto.HomeContentDTO;
import org.project.skyflow.dto.mapper.ContentMapper;
import org.project.skyflow.repository.ContentMetaDataRepository;
import org.project.skyflow.repository.ContentRepository;
import org.project.skyflow.service.ContentService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {
    private final ContentRepository contentRepository;
    private final ContentMapper contentMapper;
    private final ContentMetaDataRepository contentMetaDataRepository;

    @Override
    public List<HomeContentDTO> fetchContent() {
        return contentRepository.findAll().stream().map(contentMapper::toHomeContentDTO).toList();
    }

    @Override
    public void createContent() {
        Content content = Content.builder()
                .title("first ever content piece")
                .description("description here")
                .creator(Account.builder().id(2).build())
                .uploadDate(LocalDateTime.now())
                .status(ContentStatus.PUBLIC)
                .category(Category.builder().id(1).build())
                .build();

        content = contentRepository.save(content);

        ContentMetaData contentMetaData = ContentMetaData.builder()
                .content(content)
                .filePath("/here")
                .fileSize(50)
                .thumbnail("thumbnail")
                .duration(13.25)
                .resolution("1920*1080")
                .codec("H265")
                .processingStatus(ProcessingStatus.READY)
                .plays(0)
                .upVotes(0)
                .downVotes(0)
                .lastProcessedDate(LocalDateTime.now())
                .mimeType("2xt34")
                .storageLocation("s3/aws/cloud/test")
                .build();

        contentMetaDataRepository.save(contentMetaData);

    }

    @Override
    public void processContent(File videoFile) {
    }

    @Override
    public void convertContentFormat(File inputFile, File outputFile) {

    }


}
