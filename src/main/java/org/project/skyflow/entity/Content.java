package org.project.skyflow.entity;

import jakarta.persistence.*;
import lombok.*;
import org.project.skyflow.entity.type.ContentStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;

    @ManyToOne()
    @JoinColumn(name = "creator_id")
    private Account creator;

    private LocalDateTime uploadDate;

    @Enumerated(EnumType.ORDINAL)
    private ContentStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(fetch = FetchType.LAZY)
    private ContentMetaData contentMetaData;

    @OneToMany(mappedBy = "content")
    private List<Opinion> opinions;

    @OneToMany(mappedBy = "content")
    private List<Vote> votes;

    @ManyToMany(mappedBy = "contentSet")
    private Set<Playlist> playlists;
}
